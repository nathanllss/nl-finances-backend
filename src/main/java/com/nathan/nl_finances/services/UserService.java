package com.nathan.nl_finances.services;


import com.nathan.nl_finances.controllers.dtos.UserDto;
import com.nathan.nl_finances.exceptions.UserNotFoundException;
import com.nathan.nl_finances.mapper.UserMapper;
import com.nathan.nl_finances.model.Account;
import com.nathan.nl_finances.model.User;
import com.nathan.nl_finances.repositories.UserRepository;
import com.nathan.nl_finances.util.validators.UserValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private List<UserValidator> validators;
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Transactional
    public UserDto saveUser(final UserDto userDto) {

        log.info("Validating user: {} ", userDto.getUsername());
        validateUser(userDto);
        log.info("User validated: {} " , userDto.getUsername());

        log.info("Saving user: {} " , userDto.getUsername());
        User entity = dtoToEntity(userDto);

        log.info("Creating account for user: {} " , userDto.getUsername());
        createAccount(entity);
        log.info("Account created for user: {} " , userDto.getUsername());

        entity = userRepository.saveAndFlush(entity);
        log.info("User saved: {} ", entity.getUsername());
        return this.userToDto(entity);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream().map(this::userToDto).toList();
    }

    public UserDto findUserById(final UUID id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            log.error("User not found with id: {}", id);
            throw new UserNotFoundException("User not found");
        } else if (!user.get().getActive()) {
            log.warn("User {} is not active", user.get().getUsername());
            return UserMapper.toDto(user.get());
        } else {
            log.info("User found: {} ", user.get().getUsername());
            return UserMapper.toDto(user.get());
        }
    }

    public void deleteUserById(final UUID id) {
        if (!userRepository.existsById(id)) {
            log.error("User not found with id: {}", id);
            throw new UserNotFoundException("User not found");
        }
        if (!userRepository.findById(id).get().getActive()) {
            log.warn("User {} is not active", userRepository.findById(id).get().getUsername());
            return;
        }
        User user = userRepository.findById(id).get();
        user.setActive(false);
        userRepository.save(user);
        log.info("User {} deleted", user.getUsername());
    }

    public UserDto userToDto(User user) {
        return UserMapper.toDto(user);
    }

    public User dtoToEntity(UserDto userDto) {
        return UserMapper.toEntity(userDto);
    }

    private void createAccount(User user) {
        Account account = new Account();
        account.setCurrentBalance(BigDecimal.ZERO);
        user.setAccount(account);
    }

    private void  validateUser(UserDto userDto) {
        for (UserValidator validator : validators) {
            validator.validate(userDto);
        }
    }
}

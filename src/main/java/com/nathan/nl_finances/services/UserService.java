package com.nathan.nl_finances.services;


import com.nathan.nl_finances.controllers.dtos.UserDto;
import com.nathan.nl_finances.mapper.UserMapper;
import com.nathan.nl_finances.model.Account;
import com.nathan.nl_finances.model.User;
import com.nathan.nl_finances.repositories.UserRepository;
import com.nathan.nl_finances.util.validators.UserValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private List<UserValidator> validators;
    private final Logger logger = Logger.getLogger(UserService.class.getName());


    @Transactional
    public UserDto saveUser(final UserDto userDto) {

        logger.info("Validating user: " + userDto.getUsername());
        validateUser(userDto);
        logger.info("User validated: " + userDto.getUsername());

        logger.info("Saving user: " + userDto.getUsername());
        User entity = dtoToEntity(userDto);
        logger.info("Creating account for user: " + userDto.getUsername());
        createAccount(entity);
        logger.info("Account created for user: " + userDto.getUsername());
        entity = userRepository.saveAndFlush(entity);
        logger.info("User saved: " + entity.getUsername());
        System.out.println(entity);

        return this.userToDto(entity);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream().map(this::userToDto).toList();
    }

    public UserDto findUserById(final UUID id) {
        User user = userRepository.findById(id).orElseThrow();
        return UserMapper.toDto(user);
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

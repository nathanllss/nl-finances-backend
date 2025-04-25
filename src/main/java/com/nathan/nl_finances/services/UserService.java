package com.nathan.nl_finances.services;


import com.nathan.nl_finances.domain.entity.Account;
import com.nathan.nl_finances.domain.entity.User;
import com.nathan.nl_finances.dtos.UserDto;
import com.nathan.nl_finances.exceptions.UserNotFoundException;
import com.nathan.nl_finances.mapper.UserMapper;
import com.nathan.nl_finances.repositories.UserRepository;
import com.nathan.nl_finances.util.validators.UserValidator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private List<UserValidator> validators;

    private final Logger log = LoggerFactory.getLogger(this.getClass());


//    @Transactional(readOnly = true)
//    public UserDto getMe() {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return this.userToDto(user);
//    }

    @Transactional(readOnly = true)
    public User getUserWithAcc(Authentication auth) {
        var user = (User) auth.getPrincipal();
        var email = user.getEmailAddress();
        Account acc = new Account();
        acc.setId(this.findAccountUUIDByEmail(email));
        user.setAccount(acc);
        //user.getAccount().setId(this.findAccountUUIDByEmail(email));
        return user;
    }
    @Transactional(readOnly = true)
    public UserDto getMe(User user) {
        return this.userToDto(user);
    }
    @Transactional
    public UserDto saveUser(final UserDto userDto) {

        log.info("Validating user: {} ", userDto.getUsername());
        validateUser(userDto);
        log.info("User validated: {} " , userDto.getUsername());

        log.info("Saving user: {} " , userDto.getUsername());

        String encryptedPassword = new BCryptPasswordEncoder().encode(userDto.getPassword());
        User entity = dtoToEntity(userDto);
        entity.setPassword(encryptedPassword);

        log.info("Creating account for user: {} " , userDto.getUsername());
        accountService.createAccount(entity);
        log.info("Account created for user: {} " , userDto.getUsername());

        entity = userRepository.saveAndFlush(entity);
        log.info("User saved: {} ", entity.getUsername());
        return this.userToDto(entity);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream().map(this::userToDto).toList();
    }

    @Transactional(readOnly = true)
    public UserDto findUserById(final UUID id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            log.error("User not found with id: {}", id);
            throw new UserNotFoundException("User not found");
        } else if (!user.get().getActive()) {
            log.warn("User {} is not active", user.get().getUsername());
            return this.userToDto(user.get());
        } else {
            log.info("User found: {} ", user.get().getUsername());
            return this.userToDto(user.get());
        }
    }


    @Transactional
    public UserDto updateUser(final UUID id, final UserDto userDto) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            log.error("User not found with id: {}", id);
            throw new UserNotFoundException("User not found");
        }
        if (!user.get().getActive()) {
            log.warn("User {} is not active", user.get().getUsername());
        }

        log.info("Updating user: {} ", user.get().getUsername());
        validateUser(userDto);
        updateUser(user.get(), userDto);
        User entity = userRepository.saveAndFlush(user.get());
        log.info("User updated: {} ", entity.getUsername());
        return this.userToDto(entity);
    }

    @Transactional
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

//   protected User authenticated() {
//        try {
//            String username = customUserUtil.getLoggedUsername();
//
//            return userRepository.findByEmailAddress(username).get();
//        } catch (Exception e) {
//            throw new UsernameNotFoundException("Email not found");
//        }
//    }

    private void updateUser(User user, UserDto userDto) {
        user.setName(userDto.getName());
        user.setEmailAddress(userDto.getEmailAddress());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setActive(userDto.isActive());
    }

    private void  validateUser(UserDto userDto) {
        for (UserValidator validator : validators) {
            validator.validate(userDto);
        }
    }
    private UUID findAccountUUIDByEmail(String email) {
        return userRepository.findByEmailAddress(email)
                .map(User::getAccount)
                .map(Account::getId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}

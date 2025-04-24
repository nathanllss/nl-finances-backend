package com.nathan.nl_finances.controllers;

import com.nathan.nl_finances.controllers.dtos.LoginRequestDto;
import com.nathan.nl_finances.controllers.dtos.LoginResponseDto;
import com.nathan.nl_finances.controllers.dtos.UserDto;
import com.nathan.nl_finances.domain.entity.Account;
import com.nathan.nl_finances.domain.entity.User;
import com.nathan.nl_finances.services.TokenService;
import com.nathan.nl_finances.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

//    @PostMapping("/login")
//    public ResponseEntity login(@RequestBody @Valid LoginDto data){
//        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword());
//        var auth = this.authenticationManager.authenticate(usernamePassword);
//
//        var token = tokenService.generateToken((User) auth.getPrincipal());
//
//
//        return ResponseEntity.ok(new LoginResponseDto(token));
//    }
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequestDto data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var user = (User) auth.getPrincipal();
        Account acc = new Account();
        user.setAccount(acc);
        user.getAccount().setId(userService.findUserAccountUUIDByEmail(user.getEmailAddress()));
        var token = tokenService.generateToken(user);


        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity createUser(@Valid @RequestBody UserDto userDto) {
        userService.saveUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Account successfully created");
    }

}

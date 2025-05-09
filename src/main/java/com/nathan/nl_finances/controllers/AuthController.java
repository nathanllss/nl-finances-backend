package com.nathan.nl_finances.controllers;

import com.nathan.nl_finances.dtos.LoginRequestDto;
import com.nathan.nl_finances.dtos.LoginResponseDto;
import com.nathan.nl_finances.dtos.UserDto;
import com.nathan.nl_finances.services.TokenService;
import com.nathan.nl_finances.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequestDto data, HttpServletResponse response){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var user = userService.getUserWithAcc(auth);
        var token = tokenService.generateToken(user);

        Cookie cookie = new Cookie("access_token", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // Para HTTPS
        cookie.setPath("/");
        cookie.setMaxAge(3600); // 1 hora
        response.addCookie(cookie);


        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity createUser(@Valid @RequestBody UserDto userDto) {
        userService.saveUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Account successfully created");
    }

}

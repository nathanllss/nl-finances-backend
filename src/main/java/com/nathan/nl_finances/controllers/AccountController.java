package com.nathan.nl_finances.controllers;

import com.nathan.nl_finances.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users/{userId}/account")
public class AccountController {

    @Autowired
    private UserService userService;


}

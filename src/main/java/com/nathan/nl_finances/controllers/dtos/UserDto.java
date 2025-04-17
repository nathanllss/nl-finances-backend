package com.nathan.nl_finances.controllers.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.util.UUID;

@Value
public class UserDto {

    UUID id;
    String accountId;

    @NotBlank(message = "Name cannot be blank")
    String name;

    @Email(message = "Invalid email address")
    @NotBlank(message = "Email cannot be blank")
    String emailAddress;

    @NotBlank(message = "Phone number cannot be blank")
    String phoneNumber;

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 10, message = "Username must be between 4 and 20 characters")
    String username;

    @NotBlank(message = "Password cannot be blank")
    String password;
}
package com.nathan.nl_finances.controllers.dtos;

import lombok.Value;

@Value
public class LoginResponseDto {

    String accessToken;

    public LoginResponseDto(String token) {
        this.accessToken = token;
    }


}

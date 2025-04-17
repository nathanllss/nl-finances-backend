package com.nathan.nl_finances.mapper;

import com.nathan.nl_finances.controllers.dtos.UserDto;
import com.nathan.nl_finances.model.User;


public abstract class UserMapper {

    public static UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getAccount().getId().toString(),
                user.getName(),
                user.getEmailAddress(),
                user.getPhoneNumber(),
                user.getUsername(),
                user.getPassword(),
                user.getActive()
        );
    }


    public static User toEntity(UserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmailAddress(dto.getEmailAddress());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setActive(dto.isActive());
        return user;
    }



}
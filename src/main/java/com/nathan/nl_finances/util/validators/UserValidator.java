package com.nathan.nl_finances.util.validators;

import com.nathan.nl_finances.exceptions.InvalidInformation;
import com.nathan.nl_finances.controllers.dtos.UserDto;

public interface UserValidator {

    default void validate(UserDto user) {
        throw new InvalidInformation("Invalid user information");
    }
}

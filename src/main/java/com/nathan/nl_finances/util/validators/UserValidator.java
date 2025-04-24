package com.nathan.nl_finances.util.validators;

import com.nathan.nl_finances.exceptions.InvalidInformationException;
import com.nathan.nl_finances.dtos.UserDto;

public interface UserValidator {

    default void validate(UserDto user) {
        throw new InvalidInformationException("Invalid user information");
    }
}

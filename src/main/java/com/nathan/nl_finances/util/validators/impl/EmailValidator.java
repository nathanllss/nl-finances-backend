package com.nathan.nl_finances.util.validators.impl;

import com.nathan.nl_finances.controllers.dtos.UserDto;
import com.nathan.nl_finances.exceptions.InvalidInformation;
import com.nathan.nl_finances.util.constants.RegEx;
import com.nathan.nl_finances.util.validators.UserValidator;
import org.springframework.stereotype.Component;

@Component
public class EmailValidator implements UserValidator {

    @Override
    public void validate(UserDto dto) {
        String value = dto.getEmailAddress();
        if (value == null || value.isEmpty() || !value.matches(RegEx.EMAIL_REGEX)) {
            throw new InvalidInformation("Invalid email");
        }
    }
}

package com.nathan.nl_finances.util.validators.impl;

import com.nathan.nl_finances.dtos.UserDto;
import com.nathan.nl_finances.exceptions.InvalidInformationException;
import com.nathan.nl_finances.util.constants.RegEx;
import com.nathan.nl_finances.util.validators.UserValidator;
import org.springframework.stereotype.Component;

@Component
public class EmailValidator implements UserValidator {

    @Override
    public void validate(UserDto dto) {
        String value = dto.getEmailAddress();
        if (value == null || value.isEmpty() || !value.matches(RegEx.EMAIL_REGEX)) {
            throw new InvalidInformationException("Invalid email");
        }
    }
}

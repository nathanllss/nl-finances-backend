package com.nathan.nl_finances.util;

import com.nathan.nl_finances.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CustomUserUtil {

    public String getLoggedUsername() {
        User activeUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return activeUser.getEmailAddress();
    }
}
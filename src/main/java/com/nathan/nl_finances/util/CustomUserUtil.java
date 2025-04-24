package com.nathan.nl_finances.util;

import com.nathan.nl_finances.config.security.UserContext;
import com.nathan.nl_finances.model.User;
import com.nathan.nl_finances.model.projections.UserAccoutMinIdsDto;
import com.nathan.nl_finances.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CustomUserUtil {

    @Autowired
    private UserRepository userRepository;

    public String getLoggedUsername() {
        User activeUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return activeUser.getEmailAddress();
    }

    public UUID getLoggedUser_AccountId() {
        return UserContext.getCurrentUser().getAccountId();
    }

    public UserAccoutMinIdsDto getLoggedUserAccount() {
        var userId = this.getLoggedUser_AccountId();
        return userRepository.searchUserAndAccountIdFromAccountId(userId);
    }
}
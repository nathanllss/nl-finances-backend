package com.nathan.nl_finances.config.security;

import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserContext {
    private static final ThreadLocal<UserContextInfo> currentUser = new ThreadLocal<>();

    public static void setContext(String email, UUID accountId) {
        currentUser.set(new UserContextInfo(email, accountId));
    }

    public static UserContextInfo getCurrentUser() {
        return currentUser.get();
    }

    public static void clear() {
        currentUser.remove();
    }

    @Value
    public static class UserContextInfo {
        String email;
        UUID accountId;
    }
}


package com.nathan.nl_finances.model.projections;

import lombok.Value;

public interface UserAccoutMinIdsDto {

    String getUserId();

    String getAccountId();
    String getRole();


    @Value
    class UserAccountMinIdsImpl implements UserAccoutMinIdsDto {
        String userId;
        String accountId;
        String role;
    }
}

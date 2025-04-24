package com.nathan.nl_finances.repositories;

import com.nathan.nl_finances.model.User;
import com.nathan.nl_finances.model.projections.UserAccoutMinIdsDto;
import com.nathan.nl_finances.model.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsById(UUID id);

    User findByAccount_Id(UUID accountId);

    @SuppressWarnings("SqlNoDataSourceInspection")
    @Query(nativeQuery = true, value = """
            	SELECT tb_user.email_address AS username, tb_user.password, tb_role.id AS roleId, tb_role.authority
            	FROM tb_user
            	INNER JOIN tb_user_role ON tb_user.id = tb_user_role.user_id
            	INNER JOIN tb_role ON tb_role.id = tb_user_role.role_id
            	WHERE tb_user.email_address = :username
            """)
    List<UserDetailsProjection> searchUserAndRolesByUsername(String username);

    @SuppressWarnings("SqlNoDataSourceInspection")
    @Query(nativeQuery = true, value = """
        SELECT
            CAST(u.id AS varchar) AS userId,
            CAST(a.id AS varchar) AS accountId,
            r.authority as role
        FROM tb_user u
        LEFT JOIN tb_account a ON u.account_id = a.id
        LEFT JOIN tb_user_role ur ON u.id = ur.user_id
        LEFT JOIN tb_role r ON r.id = ur.role_id
        WHERE a.id = :id
        LIMIT 1
    """)
    UserAccoutMinIdsDto  searchUserAndAccountIdFromAccountId(UUID id);

    Optional<User> findByEmailAddress(String email);
}
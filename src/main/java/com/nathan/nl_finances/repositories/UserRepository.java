package com.nathan.nl_finances.repositories;

import com.nathan.nl_finances.model.User;
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

    Optional<User> findByEmailAddress(String email);
}
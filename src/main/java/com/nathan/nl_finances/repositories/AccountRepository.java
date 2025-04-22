package com.nathan.nl_finances.repositories;

import com.nathan.nl_finances.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    @Query("SELECT a FROM Account a " +
           "LEFT JOIN FETCH a.categories " +
           "LEFT JOIN FETCH a.budgets " +
           "WHERE a.id = :id")
    Optional<Account> searchByIdWithDetails(@Param("id") UUID id);

}
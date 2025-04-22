package com.nathan.nl_finances.repositories;

import com.nathan.nl_finances.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BudgetRepository extends JpaRepository<Budget, UUID> {

    Optional<List<Budget>> findByAccountOwner_Id(UUID accountOwnerId);
}
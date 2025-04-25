package com.nathan.nl_finances.repositories;

import com.nathan.nl_finances.domain.entity.Budget;
import com.nathan.nl_finances.projections.BudgetMinDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BudgetRepository extends JpaRepository<Budget, UUID> {

    Optional<List<Budget>> findByAccountOwner_Id(UUID accountOwnerId);

    @SuppressWarnings("SqlNoDataSourceInspection")
    @Query(nativeQuery = true, value = """
            SELECT b.id, b.name, b.planned_amount, b.spent_amount, b.period, b.start_date, b.end_date
            FROM tb_budget b
            WHERE b.account_id = :accountOwnerId
            """)
    Page<BudgetMinDto> searchBudgetByAccountOwner_Id(UUID accountOwnerId, Pageable pageable);
}
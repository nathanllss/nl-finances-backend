package com.nathan.nl_finances.repositories;

import com.nathan.nl_finances.domain.entity.Account;
import com.nathan.nl_finances.projections.AccountSummaryProjection;
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

    @SuppressWarnings("SqlNoDataSourceInspection")
    @Query(nativeQuery = true, value = """
    SELECT
        COALESCE(SUM(CASE WHEN t.type = 'INCOME' THEN t.transaction_value ELSE 0 END), 0) AS totalIncome,
        COALESCE(SUM(CASE WHEN t.type = 'EXPENSE' THEN t.transaction_value ELSE 0 END), 0) AS totalSpent,
        (SELECT CONCAT('[',
                STRING_AGG(category_json, ','),
                ']')
         FROM (
             SELECT CONCAT('{',
                    '"category":"', c.name, '",',
                    '"amount":', CAST(SUM(t2.transaction_value) as VARCHAR), ',',
                    '"color":"', c.color_hex, '"',
                    '}') as category_json
             FROM tb_transaction t2
             JOIN tb_category c ON t2.category_id = c.id
             WHERE t2.account_id = :accountId
             AND t2.type = 'EXPENSE'
             GROUP BY c.id, c.name, c.color_hex
         ) subquery
        ) AS expensesByCategoryJson
    FROM tb_transaction t
    WHERE t.account_id = :accountId
""")
    AccountSummaryProjection getAccountSummary(@Param("accountId") UUID accountId);

}
package com.nathan.nl_finances.repositories;

import com.nathan.nl_finances.controllers.dtos.TransactionMinDto;
import com.nathan.nl_finances.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    @SuppressWarnings("SqlNoDataSourceInspection")
    @Query(nativeQuery = true, value = """
    SELECT CAST(t.id as varchar) AS id, t.title, t.description, t.type, t.moment, t.transaction_value
    FROM tb_transaction t
    WHERE t.account_id = :accountId
    """)
    List<TransactionMinDto> searchTransactionsByAccountId(UUID accountId);
}
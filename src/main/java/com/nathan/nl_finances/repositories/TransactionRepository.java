package com.nathan.nl_finances.repositories;

import com.nathan.nl_finances.model.Transaction;
import com.nathan.nl_finances.model.projections.TransactionMinDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    @SuppressWarnings("SqlNoDataSourceInspection")
    @Query(nativeQuery = true, value = """
    SELECT CAST(t.id as varchar) AS id, t.title, t.description, t.type, t.moment, t.transaction_value
    FROM tb_transaction t
    WHERE t.account_id = :accountId
    """)
    Page<TransactionMinDto> searchTransactionsByAccountId(UUID accountId, Pageable pageable);

    @SuppressWarnings("SqlNoDataSourceInspection")
    @Query(nativeQuery = true, value = """
    SELECT CAST(t.id as varchar) AS id, t.title, t.description, t.type, t.moment, t.transaction_value
    FROM tb_transaction t
    WHERE t.account_id = :ownerId
    """)
    Page<TransactionMinDto> searchByOwner_Id(UUID ownerId, Pageable pageable);

}
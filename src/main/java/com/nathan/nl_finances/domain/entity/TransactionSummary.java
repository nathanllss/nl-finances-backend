package com.nathan.nl_finances.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_transaction_summary")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false, unique = true)
    private Account account;

    private BigDecimal totalIncome;

    private BigDecimal totalSpent;

    @LastModifiedDate
    private OffsetDateTime lastUpdate;

    @Lob
    private String expensesByCategoryJson;
}

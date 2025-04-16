package com.nathan.nl_finances.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_transaction_summary")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionSummary extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false, unique = true)
    private Account account;

    private BigDecimal totalIncome;

    private BigDecimal totalSpent;

    @Lob
    private String expensesByCategoryJson;
}

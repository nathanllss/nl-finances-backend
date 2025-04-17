package com.nathan.nl_finances.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.GenerationType.UUID;

@Entity
@Table(name = "tb_account")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = UUID)
    private UUID id;
    private BigDecimal currentBalance;
    @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    private List<Transaction> transactions = new ArrayList<>();
    @OneToMany(mappedBy = "owner")
    @Setter(AccessLevel.NONE)
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "accountOwner")
    @Setter(AccessLevel.NONE)
    private List<Budget> budgets = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

package com.nathan.nl_finances.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;

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
    private Set<Transaction> transactions = new HashSet<>();
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "accountOwner", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    private Set<Budget> budgets = new HashSet<>();

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

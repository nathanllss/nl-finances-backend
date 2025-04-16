package com.nathan.nl_finances.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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
    @OneToOne
    @JoinColumn(name = "user_id")
    private User owner;
    @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL)
    private List<Transaction> transactions;
    @OneToMany(mappedBy = "owner")
    private List<Category> categories;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(owner, account.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, owner);
    }
}

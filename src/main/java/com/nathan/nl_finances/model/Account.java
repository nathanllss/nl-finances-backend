package com.nathan.nl_finances.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

import static jakarta.persistence.GenerationType.UUID;

@Entity
@Table(name = "tb_account")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Account {

    @Id
    @GeneratedValue(strategy = UUID)
    private UUID id;
    private BigDecimal currentBalance;
    @OneToOne(mappedBy = "account",cascade = CascadeType.ALL)
    private User owner;
}

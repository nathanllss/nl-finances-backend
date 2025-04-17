package com.nathan.nl_finances.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.GenerationType.UUID;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseModel{

    @Id
    @GeneratedValue(strategy = UUID)
    private UUID id;

    private String name;

    @Column(unique = true)
    private String emailAddress;

    @Column(unique = true)
    private String phoneNumber;

    @Column(unique = true)
    private String username;

    private String password;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(emailAddress, user.emailAddress) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(account, user.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, emailAddress, phoneNumber, account);
    }
}

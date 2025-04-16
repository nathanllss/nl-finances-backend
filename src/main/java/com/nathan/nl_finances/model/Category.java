package com.nathan.nl_finances.model;

import com.nathan.nl_finances.model.enums.CategoryType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "tb_category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String description;
    private String imgUrl;
    private String colorHex;
    @Enumerated(EnumType.STRING)
    private CategoryType type;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account owner;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name) && Objects.equals(owner, category.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, owner);
    }
}
package com.nathan.nl_finances.domain.entity;

import com.nathan.nl_finances.domain.enums.CategoryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "tb_category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
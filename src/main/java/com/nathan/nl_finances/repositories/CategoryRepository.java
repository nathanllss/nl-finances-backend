package com.nathan.nl_finances.repositories;

import com.nathan.nl_finances.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
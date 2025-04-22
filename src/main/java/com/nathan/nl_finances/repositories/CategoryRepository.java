package com.nathan.nl_finances.repositories;

import com.nathan.nl_finances.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<List<Category>> findByOwner_Id(UUID owner_id);
}
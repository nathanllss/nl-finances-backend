package com.nathan.nl_finances.repositories;

import com.nathan.nl_finances.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<List<Category>> findByOwner_Id(UUID owner_id);

    @Query("SELECT c FROM Category c WHERE c.owner.id = :ownerId")
    Page<Category> searchByOwner_Id(@Param("ownerId") UUID ownerId, Pageable pageable);
    Optional<Category> findByOwner_IdAndName(UUID owner_id, String name);
}
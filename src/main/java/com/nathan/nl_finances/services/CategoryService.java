package com.nathan.nl_finances.services;

import com.nathan.nl_finances.controllers.dtos.CategoryDto;
import com.nathan.nl_finances.exceptions.CategoryNotFoundException;
import com.nathan.nl_finances.mapper.CategoryMapper;
import com.nathan.nl_finances.model.Category;
import com.nathan.nl_finances.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDto getCategoryById(final Long id) {
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isEmpty()) {
            throw new CategoryNotFoundException("Category not found");
        }

        return this.categoryToDto(category.get());
    }

    public Page<CategoryDto> getCategoryOfOwner(final UUID ownerId, Pageable pageable) {
        Page<Category> categories = categoryRepository.searchByOwner_Id(ownerId, pageable);

        return categories.map(this::categoryToDto);
    }

    private CategoryDto categoryToDto(Category category) {
        return CategoryMapper.toDto(category);
    }
}

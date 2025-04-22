package com.nathan.nl_finances.services;

import com.nathan.nl_finances.controllers.dtos.CategoryDto;
import com.nathan.nl_finances.exceptions.CategoryNotFoundException;
import com.nathan.nl_finances.mapper.CategoryMapper;
import com.nathan.nl_finances.model.Category;
import com.nathan.nl_finances.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<CategoryDto> getCategoryOfOwner(final UUID ownerId) {
        Optional<List<Category>> categories = categoryRepository.findByOwner_Id(ownerId);

        if (categories.isEmpty()) {
            throw new CategoryNotFoundException("No categories found for this owner");
        }

        return categories.get().stream()
                .map(this::categoryToDto)
                .toList();
    }

    private CategoryDto categoryToDto(Category category) {
        return CategoryMapper.toDto(category);
    }
}

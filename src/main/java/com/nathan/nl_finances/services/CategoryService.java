package com.nathan.nl_finances.services;

import com.nathan.nl_finances.domain.entity.Account;
import com.nathan.nl_finances.domain.entity.Category;
import com.nathan.nl_finances.dtos.CategoryDto;
import com.nathan.nl_finances.exceptions.CategoryNotFoundException;
import com.nathan.nl_finances.exceptions.DatabaseIntegrityException;
import com.nathan.nl_finances.mapper.CategoryMapper;
import com.nathan.nl_finances.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public CategoryDto getCategoryById(final Long id) {
        var category = validateCategoryOwner(id);
        return this.categoryToDto(category);
    }

    @Transactional(readOnly = true)
    public Page<CategoryDto> getCategoryOfOwner(final UUID ownerId,
                                                Pageable pageable) {
        Page<Category> categories = categoryRepository.searchByOwner_Id(ownerId, pageable);

        return categories.map(this::categoryToDto);
    }

    @Transactional
    public CategoryDto createCategory(final Account account, final CategoryDto categoryDto) {
        Category category = dtoToEntity(categoryDto);
        category.setOwner(account);
        category = categoryRepository.saveAndFlush(category);
        return categoryToDto(category);
    }

    @Transactional
    public CategoryDto updateCategory(final Long id,
                                      final CategoryDto categoryDto) {
        var entity = this.validateCategoryOwner(id);
        updateCategory(categoryDto, entity);
        entity = categoryRepository.saveAndFlush(entity);
        return this.categoryToDto(entity);
    }

    @Transactional
    public void deleteCategory(final Long id) {
        try {
            var category = this.validateCategoryOwner(id);
            categoryRepository.delete(category);
        } catch (Exception e) {
            throw new DatabaseIntegrityException("Database integrity violation");
        }
    }

    private void updateCategory(CategoryDto categoryDto,Category category) {
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setType(categoryDto.getType());
        category.setColorHex(categoryDto.getColorHex());
        category.setImgUrl(categoryDto.getImgUrl());
    }

    private Category validateCategoryOwner(final Long categoryId) {
        if (categoryRepository.existsById(categoryId)) {
            var category = categoryRepository.findById(categoryId).get();
            var categoryOwnerId = category.getOwner().getId();
            authService.validateSelfOrAdmin(categoryOwnerId);
            return category;
        } else {
            throw new CategoryNotFoundException("Category not found");
        }
    }

    private Category dtoToEntity(CategoryDto categoryDto) {
        return CategoryMapper.toEntity(categoryDto);
    }

    private CategoryDto categoryToDto(Category category) {
        return CategoryMapper.toDto(category);
    }
}

package com.nathan.nl_finances.mapper;

import com.nathan.nl_finances.controllers.dtos.CategoryDto;
import com.nathan.nl_finances.domain.entity.Category;


public abstract class CategoryMapper {

    public static CategoryDto toDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getImgUrl(),
                category.getColorHex(),
                category.getType()
        );
    }

    public static Category toEntity(CategoryDto categoryDto) {
        Category entity = new Category();
        entity.setId(categoryDto.getId());
        entity.setName(categoryDto.getName());
        entity.setDescription(categoryDto.getDescription());
        entity.setImgUrl(categoryDto.getImgUrl());
        entity.setColorHex(categoryDto.getColorHex());
        entity.setType(categoryDto.getType());
        return entity;
    }



}
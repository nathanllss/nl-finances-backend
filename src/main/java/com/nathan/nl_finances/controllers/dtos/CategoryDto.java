package com.nathan.nl_finances.controllers.dtos;

import com.nathan.nl_finances.domain.enums.CategoryType;
import lombok.Value;

@Value
public class CategoryDto {

    Long id;
    String name;
    String description;
    String imgUrl;
    String colorHex;
    CategoryType type;
}
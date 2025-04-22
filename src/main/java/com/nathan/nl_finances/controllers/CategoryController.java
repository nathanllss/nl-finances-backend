package com.nathan.nl_finances.controllers;

import com.nathan.nl_finances.controllers.dtos.CategoryDto;
import com.nathan.nl_finances.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users/{userId}/account/{accountId}/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Page<CategoryDto>> getCategories(@PathVariable String accountId, Pageable pageable) {
        Page<CategoryDto> categories = categoryService.getCategoryOfOwner(UUID.fromString(accountId), pageable);
        return ResponseEntity.ok(categories);
    }


}

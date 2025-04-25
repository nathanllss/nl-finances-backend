package com.nathan.nl_finances.controllers;

import com.nathan.nl_finances.domain.entity.User;
import com.nathan.nl_finances.dtos.CategoryDto;
import com.nathan.nl_finances.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Page<CategoryDto>> getCategories(@AuthenticationPrincipal UserDetails loggedUser, Pageable pageable) {
        Page<CategoryDto> categories = categoryService.getCategoryOfOwner(
                ((User) loggedUser).getAccount().getId(),
                pageable);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable String id) {
        CategoryDto category = categoryService.getCategoryById(Long.valueOf(id));
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@AuthenticationPrincipal UserDetails loggedUser, @RequestBody CategoryDto categoryDto) {
        CategoryDto createdCategory = categoryService.createCategory(
                ((User) loggedUser).getAccount(),
                categoryDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdCategory.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable String id, @RequestBody CategoryDto categoryDto) {
        CategoryDto updatedCategory = categoryService.updateCategory(Long.valueOf(id), categoryDto);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(Long.valueOf(id));
        return ResponseEntity.noContent().build();
    }

}

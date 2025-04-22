package com.nathan.nl_finances.controllers;

import com.nathan.nl_finances.controllers.dtos.BudgetDto;
import com.nathan.nl_finances.services.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users/{userId}/account/{accountId}/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @GetMapping
    public ResponseEntity<List<BudgetDto>> getBudgets(@PathVariable String accountId) {
        List<BudgetDto> budgets = budgetService.getBudgetsByAccount(UUID.fromString(accountId));
        return ResponseEntity.ok(budgets);
    }

    @PostMapping
    public ResponseEntity<BudgetDto> createBudget(@RequestBody BudgetDto budgetDto) {
        BudgetDto createdBudget = budgetService.createBudget(budgetDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(createdBudget.getId()).toUri();
        return ResponseEntity.created(uri).body(createdBudget);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BudgetDto> updateBudget(@PathVariable String id, @RequestBody BudgetDto budgetDto) {
        BudgetDto updatedBudget = budgetService.updateBudget(UUID.fromString(id), budgetDto);
        return ResponseEntity.ok(updatedBudget);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable String id) {
        budgetService.deleteBudget(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}

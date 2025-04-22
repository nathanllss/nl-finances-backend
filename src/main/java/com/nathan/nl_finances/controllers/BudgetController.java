package com.nathan.nl_finances.controllers;

import com.nathan.nl_finances.controllers.dtos.BudgetDto;
import com.nathan.nl_finances.services.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

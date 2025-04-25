package com.nathan.nl_finances.controllers;

import com.nathan.nl_finances.domain.entity.User;
import com.nathan.nl_finances.dtos.BudgetDetailsDto;
import com.nathan.nl_finances.dtos.BudgetDto;
import com.nathan.nl_finances.projections.BudgetMinDto;
import com.nathan.nl_finances.services.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @GetMapping
    public ResponseEntity<Page<BudgetMinDto>> getUserBudgets(@AuthenticationPrincipal UserDetails loggedUser, Pageable pageable) {
        Page<BudgetMinDto> budgets = budgetService.getBudgetsByAccount(
                ((User) loggedUser).getAccount().getId(),
                pageable);
        return ResponseEntity.ok(budgets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BudgetDetailsDto> getBudgetById(@PathVariable String id) {
        BudgetDetailsDto budget = budgetService.getBudgetById(UUID.fromString(id));
        return ResponseEntity.ok(budget);
    }

    @GetMapping("/{accountId}/all")
    public ResponseEntity<Page<BudgetMinDto>> getBudgetsByAccount(@PathVariable String accountId, Pageable pageable) {
        Page<BudgetMinDto> budgets = budgetService.getBudgetsByAccount(UUID.fromString(accountId), pageable);
        return ResponseEntity.ok(budgets);
    }

    @PostMapping
    public ResponseEntity<BudgetDto> createBudget(@AuthenticationPrincipal UserDetails loggedUser, @RequestBody BudgetDto budgetDto) {
        BudgetDto createdBudget = budgetService.createBudget(
                ((User) loggedUser).getAccount(),
                budgetDto);
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

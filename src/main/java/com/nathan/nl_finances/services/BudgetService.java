package com.nathan.nl_finances.services;

import com.nathan.nl_finances.controllers.dtos.BudgetDto;
import com.nathan.nl_finances.mapper.BudgetMapper;
import com.nathan.nl_finances.model.Budget;
import com.nathan.nl_finances.repositories.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    public BudgetDto getBudgetById(final UUID id) {
        Optional<Budget> budget = budgetRepository.findById(id);

        if (budget.isEmpty()) {
            throw new RuntimeException("Budget not found");
        }
        return this.budgetToDto(budget.get());
    }

    public List<BudgetDto> getBudgetsByAccount(final UUID accountId) {
        Optional<List<Budget>> budgets = budgetRepository.findByAccountOwner_Id(accountId);

        if (budgets.isEmpty()) {
            throw new RuntimeException("Budgets not found");
        }
        return budgets.get()
                .stream().map(this::budgetToDto)
                .toList();
    }

    private BudgetDto budgetToDto(Budget budget) {
        return BudgetMapper.toDto(budget);
    }
}

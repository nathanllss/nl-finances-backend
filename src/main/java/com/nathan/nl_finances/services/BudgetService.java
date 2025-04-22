package com.nathan.nl_finances.services;

import com.nathan.nl_finances.controllers.dtos.BudgetDto;
import com.nathan.nl_finances.mapper.BudgetMapper;
import com.nathan.nl_finances.mapper.CategoryMapper;
import com.nathan.nl_finances.model.Budget;
import com.nathan.nl_finances.repositories.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Transactional(readOnly = true)
    public BudgetDto getBudgetById(final UUID id) {
        Optional<Budget> budget = budgetRepository.findById(id);

        if (budget.isEmpty()) {
            throw new RuntimeException("Budget not found");
        }
        return this.budgetToDto(budget.get());
    }

    @Transactional(readOnly = true)
    public List<BudgetDto> getBudgetsByAccount(final UUID accountId) {
        Optional<List<Budget>> budgets = budgetRepository.findByAccountOwner_Id(accountId);

        if (budgets.isEmpty()) {
            throw new RuntimeException("Budgets not found");
        }
        return budgets.get()
                .stream().map(this::budgetToDto)
                .toList();
    }

    @Transactional
    public BudgetDto createBudget(final BudgetDto budgetDto) {
        Budget budget = dtoToEntity(budgetDto);
        //budget.setAccountOwner(getMe());
        budget = budgetRepository.saveAndFlush(budget);
        return this.budgetToDto(budget);
    }

    @Transactional
    public void deleteBudget(final UUID id) {
        Optional<Budget> budget = budgetRepository.findById(id);

        if (budget.isEmpty()) {
            throw new RuntimeException("Budget not found");
        }
        budgetRepository.delete(budget.get());
    }

    @Transactional
    public BudgetDto updateBudget(final UUID id, final BudgetDto budgetDto) {
        Optional<Budget> budget = budgetRepository.findById(id);

        if (budget.isEmpty()) {
            throw new RuntimeException("Budget not found");
        }

        Budget entity = budget.get();
        this.updateBudgetData(entity, budgetDto);
        entity = budgetRepository.save(entity);

        return this.budgetToDto(entity);
    }

    private void updateBudgetData(Budget entity, BudgetDto budgetDto) {
        entity.setName(budgetDto.getName());
        entity.setPlannedAmount(budgetDto.getPlannedAmount());
        entity.setSpentAmount(budgetDto.getSpentAmount());
        entity.setPeriod(budgetDto.getPeriod());
        entity.setStartDate(budgetDto.getStartDate());
        entity.setEndDate(budgetDto.getEndDate());
        entity.setCategories(budgetDto.getCategories()
                .stream()
                .map(CategoryMapper::toEntity)
                .collect(Collectors.toSet()));
    }

    private BudgetDto budgetToDto(Budget budget) {
        return BudgetMapper.toDto(budget);
    }

    private Budget dtoToEntity(BudgetDto budgetDto) {
        return BudgetMapper.toEntity(budgetDto);
    }
}

package com.nathan.nl_finances.mapper;

import com.nathan.nl_finances.controllers.dtos.BudgetDto;
import com.nathan.nl_finances.domain.entity.Budget;
import com.nathan.nl_finances.domain.entity.Category;

import java.util.stream.Collectors;

public abstract class BudgetMapper {

    public static BudgetDto toDto(Budget budget) {
        BudgetDto budgetDto = new BudgetDto(
                budget.getId().toString(),
                budget.getAccountOwner().getId().toString(),
                budget.getName(),
                budget.getPlannedAmount(),
                budget.getSpentAmount(),
                budget.getPeriod(),
                budget.getStartDate(),
                budget.getEndDate());
        for (Category category : budget.getCategories()) {
            budgetDto.addCategories(CategoryMapper.toDto(category));
        }
        return budgetDto;
    }

    public static Budget toEntity(BudgetDto budgetDto) {
        Budget entity = new Budget();
        entity.setName(budgetDto.getName());
        entity.setPlannedAmount(budgetDto.getPlannedAmount());
        entity.setSpentAmount(budgetDto.getSpentAmount());
        entity.setPeriod(budgetDto.getPeriod());
        entity.setStartDate(budgetDto.getStartDate());
        entity.setEndDate(budgetDto.getEndDate());
        entity.setCategories(
                budgetDto.getCategories()
                        .stream().map(CategoryMapper::toEntity).collect(Collectors.toSet()));
        return entity;
    }



}
package com.nathan.nl_finances.services;

import com.nathan.nl_finances.domain.entity.Account;
import com.nathan.nl_finances.domain.entity.Budget;
import com.nathan.nl_finances.dtos.BudgetDetailsDto;
import com.nathan.nl_finances.dtos.BudgetDto;
import com.nathan.nl_finances.exceptions.BudgetNotFoundException;
import com.nathan.nl_finances.exceptions.DatabaseIntegrityException;
import com.nathan.nl_finances.mapper.BudgetMapper;
import com.nathan.nl_finances.mapper.CategoryMapper;
import com.nathan.nl_finances.projections.BudgetMinDto;
import com.nathan.nl_finances.repositories.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;
    @Autowired
    private AuthService authService;

//    @Transactional(readOnly = true)
//    public Page<BudgetMinDto> getBudgetsOfAccount(final UUID accountId, final Pageable pageable) {
//        authService.validateSelfOrAdmin(accountId);
//        return budgetRepository.searchBudgetByAccountOwner_Id(accountId, pageable);
//    }

    @Transactional(readOnly = true)
    public BudgetDetailsDto getBudgetById(final UUID id) {
        var budget = validateBudgetOwner(id);
        return this.toDetailsDto(budget);
    }

    @Transactional(readOnly = true)
    public Page<BudgetMinDto> getBudgetsByAccount(final UUID accountId, Pageable pageable) {
        authService.validateSelfOrAdmin(accountId);
        return budgetRepository.searchBudgetByAccountOwner_Id(accountId, pageable);
    }

    @Transactional
    public BudgetDto createBudget(final Account account, final BudgetDto budgetDto) {
        Budget budget = dtoToEntity(budgetDto);
        budget.setAccountOwner(account);
        budget = budgetRepository.saveAndFlush(budget);
        return this.budgetToDto(budget);
    }

    @Transactional
    public void deleteBudget(final UUID id) {
        try {
            var budget = validateBudgetOwner(id);
            budget.getCategories().clear();
            budgetRepository.delete(budget);
        } catch (Exception e) {
            throw new DatabaseIntegrityException("Database integrity violation");
        }
    }

    @Transactional
    public BudgetDto updateBudget(final UUID id, final BudgetDto budgetDto) {
        var entity = validateBudgetOwner(id);
;
        this.updateBudgetData(entity, budgetDto);
        entity = budgetRepository.save(entity);

        return this.budgetToDto(entity);
    }

    private Budget validateBudgetOwner(final UUID budgetId) {
        if (budgetRepository.existsById(budgetId)) {
            var budget = budgetRepository.findById(budgetId).get();
            var budgetOwnerId = budget.getAccountOwner().getId();
            authService.validateSelfOrAdmin(budgetOwnerId);
            return budget;
        } else {
            throw new BudgetNotFoundException("Budget not found");
        }
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

    private BudgetDetailsDto toDetailsDto(Budget budget) {
        return BudgetMapper.toDetailsDto(budget);
    }
    private Budget dtoToEntity(BudgetDto budgetDto) {
        return BudgetMapper.toEntity(budgetDto);
    }
}

package com.expense_tracker.backend.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expense_tracker.backend.config.exception.ServiceException;
import com.expense_tracker.backend.models.ServiceResponse;
import com.expense_tracker.backend.models.entities.Expense;
import com.expense_tracker.backend.repository.CategoryRepository;
import com.expense_tracker.backend.repository.CategoryTypeRepository;
import com.expense_tracker.backend.repository.ExpenseRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExpenseService implements IExpenseService {
  private final ExpenseRepository expenseRepository;
  private final CategoryRepository categoryRepository;
  private final CategoryTypeRepository categoryTypeRepository;

  public ExpenseService(
      final ExpenseRepository expenseRepository,
      final CategoryRepository categoryRepository,
      final CategoryTypeRepository categoryTypeRepository) {
    this.expenseRepository = expenseRepository;
    this.categoryRepository = categoryRepository;
    this.categoryTypeRepository = categoryTypeRepository;
  }

  @Override
  @Transactional(readOnly = true)
  public ServiceResponse<Page<Expense>> findExpenses(
      final LocalDate startDate,
      final LocalDate endDate,
      final Long categoryId,
      final Long categoryTypeId,
      final Pageable pageable) {
    try {
      final Page<Expense> expenses = expenseRepository.findExpensesWithFilters(categoryId, categoryTypeId, startDate,
          endDate, pageable);

      if (expenses.isEmpty()) {
        return ServiceResponse.success("No expenses found", HttpStatus.NOT_FOUND);
      }

      return ServiceResponse.success("Expenses found", expenses);
    } catch (final Exception e) {
      log.error("Error finding expenses: {}", e.getMessage());
      throw new ServiceException("Error finding expenses: " + e.getMessage());
    }
  }

  @Override
  @Transactional
  public ServiceResponse<Expense> createExpense(final Expense expense) {
    try {
      // Validate category if provided
      if (expense.getCategoryId() != null &&
          !categoryRepository.findById(expense.getCategoryId()).isPresent()) {
        return ServiceResponse.failure("Category not found", HttpStatus.NOT_FOUND);
      }

      // Validate category type if provided
      if (expense.getCategoryTypeId() != null &&
          !categoryTypeRepository.findById(expense.getCategoryTypeId()).isPresent()) {
        return ServiceResponse.failure("Category type not found", HttpStatus.NOT_FOUND);
      }

      // Set id to null to avoid conflict with the database
      expense.setId(null);

      final Expense savedExpense = expenseRepository.save(expense);
      return ServiceResponse.success("Expense created", savedExpense);
    } catch (final Exception e) {
      log.error("Error creating expense: {}", e.getMessage());
      throw new ServiceException("Error creating expense: " + e.getMessage());
    }
  }
}

package com.expense_tracker.backend.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.expense_tracker.backend.models.ServiceResponse;
import com.expense_tracker.backend.models.entities.Expense;

public interface IExpenseService {
  ServiceResponse<Page<Expense>> findExpenses(
      LocalDate startDate,
      LocalDate endDate,
      Long categoryId,
      Long categoryTypeId,
      Pageable pageable);

  ServiceResponse<Expense> createExpense(Expense expense);

  // ServiceResponse<Map<String, Object>> getExpenseAnalytics(LocalDate startDate,
  // LocalDate endDate);

  // ServiceResponse<Map<Category, BigDecimal>> getExpensesByCategory(Long
  // categoryId);
}

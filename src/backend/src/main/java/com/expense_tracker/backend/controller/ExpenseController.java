package com.expense_tracker.backend.controller;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.expense_tracker.backend.models.ServiceResponse;
import com.expense_tracker.backend.models.entities.Expense;
import com.expense_tracker.backend.service.IExpenseService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/expenses")
@Tag(name = "Expense", description = "Expense API")
public class ExpenseController {
  private final IExpenseService expenseService;

  public ExpenseController(final IExpenseService expenseService) {
    this.expenseService = expenseService;
  }

  @GetMapping
  public ResponseEntity<ServiceResponse<Page<Expense>>> getExpenses(
      @RequestParam(required = false) final LocalDate startDate,
      @RequestParam(required = false) final LocalDate endDate,
      @RequestParam(required = false) final Long categoryId,
      @RequestParam(required = false) final Long categoryTypeId,
      final Pageable pageable) {
    final ServiceResponse<Page<Expense>> response = expenseService.findExpenses(startDate, endDate, categoryId,
        categoryTypeId, pageable);

    return ResponseEntity.ok(response);
  }
}

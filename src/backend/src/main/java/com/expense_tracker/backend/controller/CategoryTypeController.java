package com.expense_tracker.backend.controller;

import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense_tracker.backend.models.ServiceResponse;
import com.expense_tracker.backend.models.entities.CategoryType;
import com.expense_tracker.backend.service.ICategoryTypeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/category-types")
@Tag(name = "Category Type", description = "Category Type API")
public class CategoryTypeController {
  private final ICategoryTypeService categoryTypeService;

  public CategoryTypeController(final ICategoryTypeService categoryTypeService) {
    this.categoryTypeService = categoryTypeService;
  }

  @GetMapping
  @Operation(summary = "Get all category types")
  public ResponseEntity<ServiceResponse<Collection<CategoryType>>> findAllCategoryTypes() {
    return ResponseEntity.ok(categoryTypeService.findAllCategoryTypes());
  }
}

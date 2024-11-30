package com.expense_tracker.backend.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense_tracker.backend.models.ServiceResponse;
import com.expense_tracker.backend.models.entities.Category;
import com.expense_tracker.backend.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/categories/")
@Tag(name = "Category", description = "Category API")
public class CategoryController {
  private final CategoryService categoryService;

  @Autowired
  public CategoryController(final CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping
  @Operation(summary = "Get all categories")
  public ResponseEntity<ServiceResponse<Collection<Category>>> findAllCategories() {
    return ResponseEntity.ok(categoryService.findAllCategories());
  }
}

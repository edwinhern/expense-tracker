package com.expense_tracker.backend.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense_tracker.backend.models.ServiceResponse;
import com.expense_tracker.backend.models.entities.Category;
import com.expense_tracker.backend.service.impl.CategoryServiceImpl;

@RestController @RequestMapping("/api/v1/categories/")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping()
    public ResponseEntity<ServiceResponse<Collection<Category>>> findAllCategories() {
        return ResponseEntity.ok(categoryService.findAllCategories());
    }
}

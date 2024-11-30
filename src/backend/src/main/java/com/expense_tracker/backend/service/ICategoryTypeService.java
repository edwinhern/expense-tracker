package com.expense_tracker.backend.service;

import java.util.Collection;

import com.expense_tracker.backend.models.entities.CategoryType;

public interface ICategoryTypeService {
  Collection<CategoryType> findAllCategoryTypes();
}

package com.expense_tracker.backend.service;

import java.util.Collection;

import com.expense_tracker.backend.models.ServiceResponse;
import com.expense_tracker.backend.models.entities.Category;

public interface ICategoryService {
  ServiceResponse<Collection<Category>> findAllCategories();
}

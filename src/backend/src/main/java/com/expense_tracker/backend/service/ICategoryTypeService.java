package com.expense_tracker.backend.service;

import java.util.Collection;

import com.expense_tracker.backend.models.ServiceResponse;
import com.expense_tracker.backend.models.entities.CategoryType;

public interface ICategoryTypeService {
  ServiceResponse<Collection<CategoryType>> findAllCategoryTypes();
}

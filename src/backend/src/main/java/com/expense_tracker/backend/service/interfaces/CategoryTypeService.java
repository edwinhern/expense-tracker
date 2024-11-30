package com.expense_tracker.backend.service.interfaces;

import java.util.Collection;

import com.expense_tracker.backend.models.entities.CategoryType;

public interface CategoryTypeService {
    Collection<CategoryType> findAllCategoryTypes();
}

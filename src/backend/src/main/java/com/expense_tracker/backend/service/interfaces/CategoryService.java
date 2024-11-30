package com.expense_tracker.backend.service.interfaces;

import java.util.Collection;

import com.expense_tracker.backend.models.ServiceResponse;
import com.expense_tracker.backend.models.entities.Category;

public interface CategoryService {
    ServiceResponse<Collection<Category>> findAllCategories();
}

package com.expense_tracker.backend.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.expense_tracker.backend.models.ServiceResponse;
import com.expense_tracker.backend.models.entities.Category;
import com.expense_tracker.backend.repository.CategoryRepository;

@Service
public class CategoryService implements ICategoryService {
  private final CategoryRepository categoryRepository;

  @Autowired
  public CategoryService(final CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Override
  public ServiceResponse<Collection<Category>> findAllCategories() {
    try {
      final Collection<Category> categories = categoryRepository.findAllCategories();

      if (categories.isEmpty()) {
        return ServiceResponse.failure("No categories found", HttpStatus.NOT_FOUND);
      }

      return ServiceResponse.success("Categories found", categories);
    } catch (final Exception e) {
      return ServiceResponse.failure("Error finding categories: " + e.getMessage());
    }
  }
}

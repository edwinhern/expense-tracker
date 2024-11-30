package com.expense_tracker.backend.service;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.expense_tracker.backend.config.exception.ServiceException;
import com.expense_tracker.backend.models.ServiceResponse;
import com.expense_tracker.backend.models.entities.Category;
import com.expense_tracker.backend.repository.CategoryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryService implements ICategoryService {
  private final CategoryRepository categoryRepository;

  public CategoryService(final CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Override
  public ServiceResponse<Collection<Category>> findAllCategories() {
    try {
      final Collection<Category> categories = categoryRepository.findAllCategories();

      if (categories.isEmpty()) {
        return ServiceResponse.success("No categories found", HttpStatus.NOT_FOUND);
      }

      return ServiceResponse.success("Categories found", categories);
    } catch (final Exception e) {
      log.error("Error finding categories: " + e.getMessage());
      throw new ServiceException("Error finding categories: " + e.getMessage());
    }
  }
}

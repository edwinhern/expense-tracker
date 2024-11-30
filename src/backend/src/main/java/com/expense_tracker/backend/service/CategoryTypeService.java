package com.expense_tracker.backend.service;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.expense_tracker.backend.config.exception.ServiceException;
import com.expense_tracker.backend.models.ServiceResponse;
import com.expense_tracker.backend.models.entities.CategoryType;
import com.expense_tracker.backend.repository.CategoryTypeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryTypeService implements ICategoryTypeService {
  private final CategoryTypeRepository categoryTypeRepository;

  public CategoryTypeService(final CategoryTypeRepository categoryTypeRepository) {
    this.categoryTypeRepository = categoryTypeRepository;
  }

  @Override
  public ServiceResponse<Collection<CategoryType>> findAllCategoryTypes() {
    try {
      final Collection<CategoryType> categoryTypes = categoryTypeRepository.findAll();

      if (categoryTypes.isEmpty()) {
        return ServiceResponse.success("No category types found", HttpStatus.NOT_FOUND);
      }

      return ServiceResponse.success("Category types found", categoryTypes);
    } catch (final Exception e) {
      log.error("Error finding category types: " + e.getMessage());
      throw new ServiceException("Error finding category types: " + e.getMessage());
    }
  }
}

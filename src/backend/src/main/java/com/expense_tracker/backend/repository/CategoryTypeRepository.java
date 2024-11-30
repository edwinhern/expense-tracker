package com.expense_tracker.backend.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.expense_tracker.backend.models.entities.CategoryType;

public interface CategoryTypeRepository extends JpaRepository<CategoryType, Long> {
  @Query("SELECT ct FROM CategoryType ct")
  Collection<CategoryType> findAllCategoryTypes();
}

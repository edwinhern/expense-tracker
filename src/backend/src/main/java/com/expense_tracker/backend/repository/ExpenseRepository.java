package com.expense_tracker.backend.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.expense_tracker.backend.models.entities.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
        @Query(value = """
                        SELECT e FROM Expense e WHERE
                        (:categoryId IS NULL OR e.categoryId = :categoryId) AND
                        (:categoryTypeId IS NULL OR e.categoryTypeId = :categoryTypeId) AND
                        (CAST(:startDate AS date) IS NULL OR e.purchaseDate >= :startDate) AND
                        (CAST(:endDate AS date) IS NULL OR e.purchaseDate <= :endDate)
                        """)
        Page<Expense> findExpensesWithFilters(
                        @Param("categoryId") Long categoryId,
                        @Param("categoryTypeId") Long categoryTypeId,
                        @Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate,
                        Pageable pageable);

        @Query("""
                        SELECT e FROM Expense e WHERE \
                        e.categoryId = :categoryId AND \
                        e.purchaseDate BETWEEN :startDate AND :endDate""")
        List<Expense> findByCategoryAndDateRange(
                        @Param("categoryId") Long categoryId,
                        @Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate);

        @Query("""
                        SELECT e FROM Expense e WHERE \
                        e.categoryTypeId = :categoryTypeId AND \
                        e.purchaseDate BETWEEN :startDate AND :endDate""")
        List<Expense> findByCategoryTypeAndDateRange(
                        @Param("categoryTypeId") Long categoryTypeId,
                        @Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate);

        @Query("""
                        SELECT SUM(e.amount) FROM Expense e WHERE \
                        e.purchaseDate BETWEEN :startDate AND :endDate""")
        BigDecimal getTotalExpensesByDateRange(
                        @Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate);
}

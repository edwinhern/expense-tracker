import { useCategories, useCategoryTypes } from '@/hooks/useCategories';
import { useExpenses } from '@/hooks/useExpenses';
import { EnhancedExpense } from '@/types';
import React, { useMemo, useState } from 'react';
import { getCategoryName, getCategoryTypeName } from './Expense.utils';
import { ExpenseContent } from './components/ExpenseContent';
import { ExpenseHeader } from './components/ExpenseHeader';
import { ExpensePagination } from './components/ExpensePagination';

export const ExpensePage: React.FC = () => {
  const [page, setPage] = useState(0);
  const { data: expenses, isLoading: isExpensesLoading, error } = useExpenses({ page, size: 10 });
  const { data: categories, isLoading: isCategoriesLoading } = useCategories();
  const { data: categoryTypes, isLoading: isCategoryTypesLoading } = useCategoryTypes();

  const isPageLoading = isExpensesLoading || isCategoriesLoading || isCategoryTypesLoading;

  const expenseRecords = useMemo<EnhancedExpense[]>(() => {
    if (!expenses?.content.length) return [];

    return expenses.content.map(expense => ({
      ...expense,
      categoryTypeName: getCategoryTypeName(categoryTypes || [], expense.categoryId) ?? 'N/A',
      categoryName: getCategoryName(categories || [], expense.categoryId) ?? 'N/A',
    }));
  }, [expenses, categoryTypes, categories]);

  return (
    <div className="space-y-4">
      <h2 className="text-2xl font-bold">Expenses</h2>

      {/* Header */}
      <ExpenseHeader />

      {/* Content */}
      <div className="space-y-2">
        <ExpenseContent isLoading={isPageLoading} records={expenseRecords} error={error} />
      </div>

      {/* Pagination */}
      <ExpensePagination totalPages={expenses?.totalPages ?? 0} page={page} setPage={setPage} />
    </div>
  );
};

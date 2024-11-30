import { useCategories, useCategoryTypes } from '@/hooks/useCategories';
import { useExpenses } from '@/hooks/useExpenses';
import { Category, CategoryType, Expense } from '@/types';
import { useMemo, useState } from 'react';

type EnhancedExpense = Expense & {
  categoryTypeName: string;
  categoryName: string;
};

const ExpenseRow = ({ expense }: { expense: EnhancedExpense }) => (
  <div className="p-4 border rounded-lg flex flex-row gap-2 justify-between">
    <p>{expense.id}</p>
    <p>{expense.categoryName}</p>
    <p>{expense.categoryTypeName}</p>
    <p>${expense.amount}</p>
    <p>{expense.purchaseDate}</p>
  </div>
);

function getCategoryTypeName(categoryTypes: CategoryType[], categoryId?: number) {
  return categoryTypes.find(ct => ct.categoryId === categoryId)?.name;
}

function getCategoryName(categories: Category[], categoryId?: number) {
  return categories.find(c => c.id === categoryId)?.name;
}

export function ExpenseList() {
  const [page, setPage] = useState(0);
  const { data: expenses, isLoading, error } = useExpenses({ page, size: 10 });
  const { data: categories } = useCategories();
  const { data: categoryTypes } = useCategoryTypes();

  const expenseRecords = useMemo<EnhancedExpense[]>(() => {
    if (!expenses?.content.length) return [];

    return expenses.content.map(expense => ({
      ...expense,
      categoryTypeName: getCategoryTypeName(categoryTypes || [], expense.categoryId) ?? 'N/A',
      categoryName: getCategoryName(categories || [], expense.categoryId) ?? 'N/A',
    }));
  }, [expenses, categoryTypes, categories]);

  if (error) {
    return (
      <div className="p-4 bg-red-100 text-red-700 rounded-lg">
        Error loading expenses: {error.message}
      </div>
    );
  }

  if (isLoading) return <div>Loading...</div>;
  if (!expenses?.content.length) return <div>No expenses found</div>;

  return (
    <div className="space-y-4">
      <h2 className="text-2xl font-bold">Expenses</h2>

      {/* Header */}
      <div className="p-4 border rounded-lg flex flex-row gap-2 justify-between">
        <p>ID</p>
        <p>Category</p>
        <p>Category Type</p>
        <p>Amount</p>
        <p>Date</p>
      </div>

      {/* Content */}
      <div className="space-y-2">
        {isLoading ? (
          <div className="animate-pulse space-y-2">
            {[...Array(3)].map((_, i) => (
              <div key={i} className="h-14 bg-gray-200 rounded-lg" />
            ))}
          </div>
        ) : expenseRecords.length === 0 ? (
          <div className="text-center py-8 text-gray-500">No expenses found</div>
        ) : (
          expenseRecords.map(expense => <ExpenseRow key={expense.id} expense={expense} />)
        )}
      </div>

      {/* Pagination */}
      {expenses?.totalPages > 1 && (
        <div className="flex justify-center gap-2">
          {Array.from({ length: expenses.totalPages }, (_, i) => (
            <button
              key={i}
              onClick={() => setPage(i)}
              className={`px-3 py-1 border rounded ${
                page === i ? 'bg-blue-500 text-white' : 'hover:bg-gray-100'
              }`}
            >
              {i + 1}
            </button>
          ))}
        </div>
      )}
    </div>
  );
}

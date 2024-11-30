import { useCategories, useCategoryTypes } from '@/hooks/useCategories';
import { useExpenses } from '@/hooks/useExpenses';
import { Category, CategoryType } from '@/types';
import { useMemo, useState } from 'react';

function getCategoryTypeName(categoryTypes: CategoryType[], categoryId?: number) {
  return categoryTypes.find(ct => ct.categoryId === categoryId)?.name;
}

function getCategoryName(categories: Category[], categoryId?: number) {
  return categories.find(c => c.id === categoryId)?.name;
}

export function ExpenseList() {
  const [page, setPage] = useState(0);
  const { data, isLoading, error } = useExpenses({ page, size: 10 });
  const { data: categories } = useCategories();
  const { data: categoryTypes } = useCategoryTypes();

  const expenseRecord = useMemo(() => {
    return data?.data.content.map(expense => ({
      ...expense,
      categoryTypeName: getCategoryTypeName(categoryTypes || [], expense.categoryId),
      categoryName: getCategoryName(categories || [], expense.categoryId),
    }));
  }, [data, categoryTypes, categories]);

  if (isLoading) return <div>Loading...</div>;
  if (error) return <div>Error loading expenses</div>;
  if (!data?.data.content.length) return <div>No expenses found</div>;

  return (
    <div className="space-y-4">
      <h2 className="text-2xl font-bold">Expenses</h2>
      <div className="p-4 border rounded-lg flex flex-row gap-2 justify-between">
        <p>ID</p>
        <p>Category</p>
        <p>Category Type</p>
        <p>Amount</p>
        <p>Date</p>
      </div>
      <div className="space-y-2">
        {expenseRecord?.map(expense => (
          <div
            key={expense.id}
            className="p-4 border rounded-lg flex flex-row gap-2 justify-between"
          >
            <p>{expense.id}</p>
            <p>{expense.categoryName ?? 'N/A'}</p>
            <p>{expense.categoryTypeName ?? 'N/A'}</p>
            <p>${expense.amount}</p>
            <p>{expense.purchaseDate}</p>
          </div>
        ))}
      </div>

      {/* Pagination */}
      <div className="flex justify-center gap-2">
        {Array.from({ length: data.data.totalPages }, (_, i) => (
          <button
            key={i}
            onClick={() => setPage(i)}
            className={`px-3 py-1 border rounded ${page === i ? 'bg-blue-500 text-white' : ''}`}
          >
            {i + 1}
          </button>
        ))}
      </div>
    </div>
  );
}

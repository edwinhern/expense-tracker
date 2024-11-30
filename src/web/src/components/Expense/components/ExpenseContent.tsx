import { EnhancedExpense } from '@/types';
import { ExpenseRow } from './ExpenseRow';

interface ExpenseContentProps {
  error: Error | null;
  isLoading: boolean;
  records: EnhancedExpense[];
}

export const ExpenseContent: React.FC<ExpenseContentProps> = ({ isLoading, records, error }) => {
  if (isLoading) {
    return (
      <div className="animate-pulse space-y-2">
        {[...Array(3)].map((_, i) => (
          <div key={`expense-skeleton-${i}`} className="h-14 bg-gray-200 rounded-lg" />
        ))}
      </div>
    );
  }

  if (error) {
    return (
      <div className="text-center py-8 text-red-500 rounded-lg border p-4">
        Error loading expenses: {error.message}
      </div>
    );
  }

  if (records.length === 0) {
    return <div className="text-center py-8 text-gray-500">No expenses found</div>;
  }

  return (
    <>
      {records.map(expense => (
        <ExpenseRow key={expense.id} expense={expense} />
      ))}
    </>
  );
};

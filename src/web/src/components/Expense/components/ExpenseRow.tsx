import { EnhancedExpense } from '@/types';

interface ExpenseRowProps {
  expense: EnhancedExpense;
}

export const ExpenseRow: React.FC<ExpenseRowProps> = ({ expense }) => (
  <div className="p-4 border rounded-lg flex flex-row gap-2 justify-between">
    <p>{expense.id}</p>
    <p>{expense.categoryName}</p>
    <p>{expense.categoryTypeName}</p>
    <p>${expense.amount}</p>
    <p>{expense.purchaseDate}</p>
  </div>
);

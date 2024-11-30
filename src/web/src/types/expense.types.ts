export interface Expense {
  id?: number;
  categoryId?: number;
  categoryTypeId?: number;
  amount: number;
  purchaseDate: string;
  createdAt?: string;
  updatedAt?: string;
}

export interface EnhancedExpense extends Expense {
  categoryTypeName: string;
  categoryName: string;
}

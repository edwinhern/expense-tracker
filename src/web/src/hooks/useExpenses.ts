import { api } from '@/lib/axios';
import { Expense, PaginatedResponse, ServiceResponse } from '@/types';
import { useQuery } from '@tanstack/react-query';

type PaginatedExpense = PaginatedResponse<Expense>;
type ExpenseResponse = ServiceResponse<PaginatedExpense>;

export function useExpenses(params?: {
  startDate?: string;
  endDate?: string;
  categoryId?: number;
  categoryTypeId?: number;
  page?: number;
  size?: number;
}) {
  return useQuery({
    queryKey: ['expenses', params],
    queryFn: async () => {
      const { data } = await api.get<ExpenseResponse>('/api/v1/expenses', { params });

      if (!data.success) {
        throw new Error(data.message);
      }

      return data.data;
    },
  });
}

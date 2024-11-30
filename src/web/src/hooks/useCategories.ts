import { api } from '@/lib/axios';
import type { Category, CategoryType, ServiceResponse } from '@/types';
import { useQuery } from '@tanstack/react-query';

type CategoryResponse = ServiceResponse<Category[]>;
type CategoryTypeResponse = ServiceResponse<CategoryType[]>;

export function useCategories() {
  return useQuery({
    queryKey: ['categories'],
    queryFn: async () => {
      const { data } = await api.get<CategoryResponse>('/api/v1/categories');

      if (!data.success) {
        throw new Error(data.message);
      }

      return data.data;
    },
  });
}

export function useCategoryTypes() {
  return useQuery({
    queryKey: ['category-types'],
    queryFn: async () => {
      const { data } = await api.get<CategoryTypeResponse>('/api/v1/category-types');

      if (!data.success) {
        throw new Error(data.message);
      }

      return data.data;
    },
  });
}

import { Category, CategoryType } from '@/types';

export function getCategoryTypeName(categoryTypes: CategoryType[], categoryId?: number) {
  return categoryTypes.find(ct => ct.categoryId === categoryId)?.name;
}

export function getCategoryName(categories: Category[], categoryId?: number) {
  return categories.find(c => c.id === categoryId)?.name;
}

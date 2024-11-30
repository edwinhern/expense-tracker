export interface Category {
  id: number;
  name: string;
  description: string;
  createdAt: string;
}

export interface CategoryType {
  id: number;
  categoryId: number;
  name: string;
  description: string;
  createdAt: string;
}

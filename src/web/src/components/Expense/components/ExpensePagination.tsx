interface ExpensePaginationProps {
  totalPages: number;
  page: number;
  setPage: (page: number) => void;
}

export const ExpensePagination: React.FC<ExpensePaginationProps> = ({
  totalPages,
  page,
  setPage,
}) => {
  if (!totalPages || totalPages <= 1) return null;

  return (
    <div className="flex justify-center gap-2">
      {Array.from({ length: totalPages }, (_, i) => (
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
  );
};

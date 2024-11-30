-- Insert initial categories
INSERT INTO categories (name, description, created_at) VALUES
    ('Housing', 'Housing and accommodation related expenses', CURRENT_TIMESTAMP),
    ('Transportation', 'All transportation related costs', CURRENT_TIMESTAMP),
    ('Food', 'Food and dining expenses', CURRENT_TIMESTAMP),
    ('Utilities', 'Utility bills and services', CURRENT_TIMESTAMP),
    ('Healthcare', 'Health and medical related expenses', CURRENT_TIMESTAMP),
    ('Entertainment', 'Leisure and entertainment costs', CURRENT_TIMESTAMP),
    ('Education', 'Educational expenses', CURRENT_TIMESTAMP),
    ('Shopping', 'Personal shopping expenses', CURRENT_TIMESTAMP);

-- Insert category types
INSERT INTO category_types (category_id, name, description, created_at) VALUES
    -- Housing types
    (1, 'Rent', 'Monthly rental payments', CURRENT_TIMESTAMP),
    (1, 'Mortgage', 'Monthly mortgage payments', CURRENT_TIMESTAMP),
    (1, 'Repairs', 'Home repairs and maintenance', CURRENT_TIMESTAMP),
    (1, 'Insurance', 'Home insurance', CURRENT_TIMESTAMP),

    -- Transportation types
    (2, 'Car Payment', 'Vehicle loan or lease payments', CURRENT_TIMESTAMP),
    (2, 'Fuel', 'Gasoline or charging costs', CURRENT_TIMESTAMP),
    (2, 'Maintenance', 'Vehicle maintenance and repairs', CURRENT_TIMESTAMP),
    (2, 'Public Transport', 'Bus, train, or subway fares', CURRENT_TIMESTAMP),

    -- Food types
    (3, 'Groceries', 'Supermarket purchases', CURRENT_TIMESTAMP),
    (3, 'Dining Out', 'Restaurant meals', CURRENT_TIMESTAMP),
    (3, 'Takeout', 'Food delivery and takeaway', CURRENT_TIMESTAMP),

    -- Utilities types
    (4, 'Electricity', 'Electric utility bills', CURRENT_TIMESTAMP),
    (4, 'Water', 'Water utility bills', CURRENT_TIMESTAMP),
    (4, 'Gas', 'Gas utility bills', CURRENT_TIMESTAMP),
    (4, 'Internet', 'Internet service bills', CURRENT_TIMESTAMP),
    (4, 'Phone', 'Mobile or landline phone bills', CURRENT_TIMESTAMP);

-- Insert sample expenses
INSERT INTO expenses (category_id, category_type_id, amount, purchase_date, created_at, updated_at) VALUES
    (1, 1, 1200.00, CURRENT_DATE - INTERVAL '1 month', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),  -- Rent
    (3, 9, 150.50, CURRENT_DATE - INTERVAL '15 days', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),   -- Groceries
    (4, 12, 85.75, CURRENT_DATE - INTERVAL '7 days', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);    -- Electricity

-- Insert sample investments
INSERT INTO investments (type, amount, purchase_date, current_value, notes, created_at, updated_at) VALUES
    ('Stocks', 1000.00, CURRENT_DATE - INTERVAL '6 months', 1150.00, 'Tech company shares', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Bonds', 2000.00, CURRENT_DATE - INTERVAL '1 year', 2100.00, 'Government bonds', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Mutual Fund', 500.00, CURRENT_DATE - INTERVAL '3 months', 525.00, 'Index fund investment', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

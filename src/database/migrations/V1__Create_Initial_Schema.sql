CREATE TABLE categories (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE category_types (
    id BIGSERIAL PRIMARY KEY,
    category_id BIGINT REFERENCES categories(id),
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP NOT NULL,
    UNIQUE(category_id, name)
);

CREATE TABLE expenses (
    id BIGSERIAL PRIMARY KEY,
    category_id BIGINT REFERENCES categories(id),
    category_type_id BIGINT REFERENCES category_types(id),
    amount DECIMAL(10,2) NOT NULL,
    purchase_date DATE NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE TABLE investments (
    id BIGSERIAL PRIMARY KEY,
    type VARCHAR(255) NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    purchase_date DATE NOT NULL,
    current_value DECIMAL(10,2),
    notes TEXT,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

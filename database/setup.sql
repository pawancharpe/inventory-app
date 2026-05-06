-- Run this in MySQL Workbench or terminal before starting the app

CREATE DATABASE IF NOT EXISTS inventory_db;
USE inventory_db;

-- The 'products' table is auto-created by Spring Boot (JPA ddl-auto=update)
-- But you can also create it manually:

CREATE TABLE IF NOT EXISTS products (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255)   NOT NULL,
    description VARCHAR(500),
    price       DECIMAL(10,2)  NOT NULL,
    quantity    INT            NOT NULL DEFAULT 0,
    category    VARCHAR(100),
    sku         VARCHAR(100)   UNIQUE,
    created_at  DATETIME,
    updated_at  DATETIME
);

-- Sample data (optional - to test the app right away)
INSERT INTO products (name, description, price, quantity, category, sku, created_at, updated_at) VALUES
('Laptop Pro 15"',   'High-performance laptop',   85000.00, 12,  'Electronics',  'LAP-001', NOW(), NOW()),
('Wireless Mouse',   'Ergonomic wireless mouse',    1299.00, 45,  'Electronics',  'MOU-001', NOW(), NOW()),
('USB-C Hub',        '7-in-1 USB-C hub',            2499.00,  3,  'Accessories',  'HUB-001', NOW(), NOW()),
('Office Chair',     'Adjustable ergonomic chair', 18500.00,  8,  'Furniture',    'CHR-001', NOW(), NOW()),
('Desk Lamp',        'LED desk lamp',               1850.00,  0,  'Furniture',    'LMP-001', NOW(), NOW()),
('Mechanical Keyboard','RGB mechanical keyboard',   4500.00, 20,  'Electronics',  'KBD-001', NOW(), NOW()),
('Monitor 27"',      'Full HD IPS monitor',        22000.00,  5,  'Electronics',  'MON-001', NOW(), NOW()),
('Notebook (100pg)', 'Ruled notebook',               120.00, 200, 'Stationery',   'NTB-001', NOW(), NOW());

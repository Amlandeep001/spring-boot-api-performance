CREATE TABLE test.category (
  id bigint PRIMARY KEY,
  name varchar(255),
  type varchar(255),
  status varchar(255)
);

CREATE TABLE test.products (
  id bigint PRIMARY KEY,
  category_id bigint,
  name varchar(255),
  description text,
  status varchar(255)
);

CREATE TABLE test.price (
  id bigint PRIMARY KEY,
  product_id bigint,
  price double precision NOT NULL,
  valid_from timestamp,
  valid_to timestamp,
  status varchar(255)
);

CREATE TABLE test.inventory (
  id bigint PRIMARY KEY,
  product_id bigint,
  warehouse_id bigint,
  available_quantity integer,
  reserved_quantity integer,
  status varchar(255)
);

ALTER TABLE test.inventory ADD FOREIGN KEY (product_id) REFERENCES test.products (id);

ALTER TABLE test.products ADD FOREIGN KEY (category_id) REFERENCES test.category (id);

ALTER TABLE test.price ADD FOREIGN KEY (product_id) REFERENCES test.products (id);


-- Insert data into category table
INSERT INTO test.category (id, name, type, status) VALUES
(1, 'Electronics', 'Goods', 'active'),
(2, 'Clothing', 'Goods', 'active'),
(3, 'Home Appliances', 'Goods', 'active');

-- Insert data into products table
INSERT INTO test.products (id, category_id, name, description, status) VALUES
(1, 1, 'Smartphone', 'Latest smartphone with high-speed performance', 'active'),
(2, 1, 'Laptop', '15-inch laptop with 8GB RAM, 256GB SSD', 'active'),
(3, 2, 'Jacket', 'Waterproof jacket for outdoor activities', 'active'),
(4, 3, 'Air Conditioner', '1.5 ton AC with energy-efficient cooling', 'active');

-- Insert data into price table
INSERT INTO test.price (id, product_id, price, valid_from, valid_to, status) VALUES
(1, 1, 699.99, NOW(), NOW() + INTERVAL '1 year', 'active'),
(2, 2, 1299.99, NOW(), NOW() + INTERVAL '1 year', 'active'),
(3, 3, 59.99, NOW(), NOW() + INTERVAL '1 year', 'active'),
(4, 4, 299.99, NOW(), NOW() + INTERVAL '1 year', 'active');

-- Insert data into inventory table
INSERT INTO test.inventory (id, product_id, warehouse_id, available_quantity, reserved_quantity, status) VALUES
(1, 1, 1, 100, 10, 'available'),
(2, 2, 1, 50, 5, 'available'),
(3, 3, 2, 200, 20, 'available'),
(4, 4, 3, 30, 2, 'available');


-- fetch data from table
SELECT * from test.products p ;

SELECT * FROM test.category c ;

SELECT * FROM test.inventory i ;

SELECT * FROM test.price p ;
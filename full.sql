DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), cost int);
INSERT INTO products (title, cost) VALUES
('apple', 110),
('peach', 120),
('pear', 100),
('cherry', 150),
('blueberry', 500);

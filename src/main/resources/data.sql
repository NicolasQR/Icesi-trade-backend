INSERT INTO role (name) VALUES ('ADMIN');
INSERT INTO role (name) VALUES ('USER');
INSERT INTO role (name) VALUES ('SELLER');

-- Luego los usuarios, referenciando los roles (que ya se insertaron con id 1, 2, 3 si están en orden)
INSERT INTO usuarios (name, email, password, role_id) VALUES
                                                          ('Nico Admin', 'admin@example.com', '1234', 1),
                                                          ('Nico User', 'user@example.com', '1234', 2),
                                                          ('Nico Seller', 'nico@example.com', '1234', 3);

-- Categoría
INSERT INTO categories (name) VALUES ('Electronics');

-- Producto publicado por el último usuario creado (que tiene id 3 si el orden se mantiene)
INSERT INTO products (
    title, description, price, status, location, image_url, user_id, category_id
) VALUES (
             'Wireless Mouse', 'Ergonomic wireless mouse', 25.00, 'Available', 'Cali',
             'https://image.url/mouse.jpg', 3, 1
         );

-- Primero los roles (asegúrate de no repetir IDs)
INSERT INTO role (id, name) VALUES (1, 'ADMIN');
INSERT INTO role (id, name) VALUES (2, 'USER');
INSERT INTO role (id, name) VALUES (3, 'SELLER');  -- Si deseas diferenciarlo

-- Luego los usuarios, referenciando los roles
INSERT INTO usuarios (id, name, email, password, role_id) VALUES
                                                              (1, 'Nico Admin', 'admin@example.com', '1234', 1),
                                                              (2, 'Nico User', 'user@example.com', '1234', 2),
                                                              (3, 'Nico Seller', 'nico@example.com', '1234', 3);  -- Este es el original

-- Categoría para el producto
INSERT INTO categories (id, name) VALUES (1, 'Electronics');

-- Producto publicado por el usuario con id = 3 (Nico Seller)
INSERT INTO products (
    id, title, description, price, status, location, image_url, user_id, category_id
) VALUES (
             1, 'Wireless Mouse', 'Ergonomic wireless mouse', 25.00, 'Available', 'Cali',
             'https://image.url/mouse.jpg', 3, 1
         );

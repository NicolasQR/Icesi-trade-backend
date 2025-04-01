-- Insertar permisos
INSERT INTO permission (name) VALUES
                                  ('READ_USERS'),         -- id 1
                                  ('CREATE_PRODUCT'),     -- id 2
                                  ('DELETE_PRODUCT'),     -- id 3
                                  ('MANAGE_ROLES'),       -- id 4
                                  ('favorite:read'),      -- id 5
                                  ('favorite:write'),     -- id 6
                                  ('favorite:delete');    -- id 7

-- Insertar roles
INSERT INTO role (name) VALUES
                            ('ADMIN'),  -- id 1
                            ('USER'),   -- id 2
                            ('SELLER'); -- id 3

-- Asociar permisos con roles

-- ADMIN tiene todos los permisos
INSERT INTO role_permission (role_id, permission_id) VALUES
                                                         (1, 1),
                                                         (1, 2),
                                                         (1, 3),
                                                         (1, 4),
                                                         (1, 5),
                                                         (1, 6),
                                                         (1, 7);

-- USER solo puede leer usuarios y leer favoritos
INSERT INTO role_permission (role_id, permission_id) VALUES
                                                         (2, 1),
                                                         (2, 5);

-- SELLER puede crear y eliminar productos, leer y eliminar favoritos
INSERT INTO role_permission (role_id, permission_id) VALUES
                                                         (3, 2),
                                                         (3, 3),
                                                         (3, 5),
                                                         (3, 7);

-- Insertar usuarios con roles
INSERT INTO usuarios (name, email, password, role_id) VALUES
                                                          ('Nico Admin', 'admin@example.com', '1234', 1),
                                                          ('Nico User', 'user@example.com', '1234', 2),
                                                          ('Nico Seller', 'nico@example.com', '1234', 3);

-- Insertar una categoría
INSERT INTO categories (name) VALUES ('Electronics');

-- Insertar un producto del usuario con rol SELLER
INSERT INTO products (
    title, description, price, status, location, image_url, user_id, category_id
) VALUES (
             'Wireless Mouse', 'Ergonomic wireless mouse', 25.00, 'Available', 'Cali',
             'https://image.url/mouse.jpg', 3, 1
         );

-- Permisos para mensajes
INSERT INTO permission (name) VALUES
                                  ('message:read'),   -- id 8
                                  ('message:write');  -- id 9

-- Admin tiene ambos permisos
INSERT INTO role_permission (role_id, permission_id) VALUES
                                                         (1, 8),
                                                         (1, 9);

-- User y Seller también pueden enviar y leer mensajes
INSERT INTO role_permission (role_id, permission_id) VALUES
                                                         (2, 8), (2, 9),
                                                         (3, 8), (3, 9);
-- Permisos para calificaciones
INSERT INTO permission (name) VALUES
                                  ('rating:read'),    -- id 10
                                  ('rating:write');   -- id 11

-- Admin tiene ambos
INSERT INTO role_permission (role_id, permission_id) VALUES
                                                         (1, 10),
                                                         (1, 11);

-- User y Seller pueden leer y escribir
INSERT INTO role_permission (role_id, permission_id) VALUES
                                                         (2, 10), (2, 11),
                                                         (3, 10), (3, 11);

-- Permisos para notificaciones
INSERT INTO permission (name) VALUES
                                  ('notification:read'),    -- id 12
                                  ('notification:write');   -- id 13

-- Todos los roles pueden leer y marcar
INSERT INTO role_permission (role_id, permission_id) VALUES
                                                         (1, 12), (1, 13),
                                                         (2, 12), (2, 13),
                                                         (3, 12), (3, 13);


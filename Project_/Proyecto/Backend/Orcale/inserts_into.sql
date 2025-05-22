<<<<<<< HEAD

USE esquema_reto;

INSERT INTO Categorias (ID_Categoria, Nombre) VALUES
(1, 'Burger'),
(2, 'Drink'),
(3, 'Sides'),
(4, 'Dessert');

INSERT INTO Ofertas (ID_Oferta, Nombre, Precio, descripcion, columnaImagen, fechaExpiracion) VALUES
(1, 'Classic for 7$', 9.99, 'Enjoy the classic flavor with a special 20% discount. An offer you can’t resist!', '/Proyecto/Frontend/Web/src/products/promos/classic-burger-promo.png','2025-05-31'),
(2, 'Ecologic 10%', 3.50, 'Eco-friendly burger made with fresh and natural ingredients. Healthy and delicious.', '/Proyecto/Frontend/Web/src/products/promos/ecologic-burger-promo.png','2025-07-31'),
(3, 'Silencer 50%', 3.50, 'The quietest but mightiest deal: 50% off on this tasty choice!', '/Proyecto/Frontend/Web/src/products/promos/silencer-burger-promo.png','2025-05-31'),
(4, 'Maneater 20%', 9.99, 'A hearty burger with a 20% discount. Perfect for those who love bold flavors.', '/Proyecto/Frontend/Web/src/products/promos/maneater-burger-promo.png','2025-06-30'),
(5, 'BBQ Classic 50%', 9.99, 'Classic BBQ with a juicy smoky twist and 50% off. Don’t miss out!', '/Proyecto/Frontend/Web/src/products/promos/bbq-burger-promo.png','2025-06-30');

INSERT INTO Ingredientes (ID_Ingrediente, Nombre, unidadMedida, stockDisponible, tipoAlmacenamiento, EstaActivo) VALUES
(1, 'Beef patty', 'kg', 50.0, 'Refrigerated', 1),
(2, 'Lettuce', 'kg', 20.0, 'Refrigerated', 1),
(3, 'Burger bun', 'units', 100, 'Dry', 1),
(4, 'Cheddar cheese', 'kg', 15.0, 'Refrigerated', 1),
(5, 'Tomato', 'kg', 10.0, 'Refrigerated', 1),
(6, 'Onion', 'kg', 10.0, 'Dry', 1),
(7, 'Pickles', 'kg', 18.0, 'Refrigerated', 1),
(8, 'Bacon', 'kg', 12.0, 'Refrigerated', 1),
(9, 'Chicken breast', 'kg', 18.0, 'Refrigerated', 1),
(10, 'Spicy sauce', 'liters', 15.0, 'Dry', 1),
(11, 'Ketchup', 'liters', 10.0, 'Dry', 1),
(12, 'Mayonnaise', 'liters', 17.0, 'Refrigerated', 1),
(13, 'Barbecue sauce', 'liters', 16.0, 'Dry', 1),
(14, 'Egg', 'units', 40, 'Refrigerated', 1),
(15, 'Mustard', 'liters', 15.0, 'Dry', 1),
(16, 'Vegan patty', 'kg', 12.0, 'Frozen', 1),
(17, 'Tofu', 'kg', 10.0, 'Refrigerated', 1),
(18, 'Mozzarella cheese', 'kg', 10.0, 'Refrigerated', 1),
(19, 'Jalapeños', 'kg', 16.0, 'Refrigerated', 1),
(20, 'Avocado', 'kg', 18.0, 'Refrigerated', 1),
(21, 'Pineapple', 'kg', 15.0, 'Refrigerated', 1),
(22, 'Chorizo', 'kg', 16.0, 'Refrigerated', 1),
(23, 'Ciabatta bun', 'units', 50, 'Dry', 1),
(24, 'Whole grain bun', 'units', 60, 'Dry', 1),
(25, 'Gluten-free bun', 'units', 30, 'Dry', 1),
(26, 'Grilled onions', 'kg', 14.0, 'Refrigerated', 1),
(27, 'Caramelized onions', 'kg', 14.0, 'Refrigerated', 1),
(28, 'Cucumber', 'kg', 15.0, 'Refrigerated', 1),
(29, 'Spinach', 'kg', 14.0, 'Refrigerated', 1),
(30, 'Blue cheese', 'kg', 13.0, 'Refrigerated', 1),
(31, 'Swiss cheese', 'kg', 16.0, 'Refrigerated', 1),
(32, 'Fried onion rings', 'units', 50, 'Frozen', 1),
(33, 'French fries', 'kg', 25.0, 'Frozen', 1),
(34, 'Sweet potato fries', 'kg', 10.0, 'Frozen', 1),
(35, 'Coleslaw', 'kg', 18.0, 'Refrigerated', 1),
(36, 'Beetroot', 'kg', 13.0, 'Refrigerated', 1),
(37, 'Red pepper', 'kg', 14.0, 'Refrigerated', 1),
(38, 'Green pepper', 'kg', 14.0, 'Refrigerated', 1),
(39, 'Iceberg lettuce', 'kg', 16.0, 'Refrigerated', 1),
(40, 'Arugula', 'kg', 14.0, 'Refrigerated', 1),
(41, 'Chili sauce', 'liters', 12.0, 'Dry', 1),
(42, 'Garlic aioli', 'liters', 13.0, 'Refrigerated', 1),
(43, 'Honey mustard', 'liters', 12.5, 'Dry', 1),
(44, 'Truffle mayo', 'liters', 11.5, 'Refrigerated', 1),
(45, 'Pastrami', 'kg', 15.0, 'Refrigerated', 1),
(46, 'Crispy onions', 'kg', 13.0, 'Dry', 1),
(47, 'Apple slices', 'kg', 12.0, 'Refrigerated', 1),
(48, 'Sun-dried tomatoes', 'kg', 12.0, 'Dry', 1),
(49, 'Cola', 'liters', 20.0, 'Dry', 1),
(50, 'Pepsi', 'liters', 15.0, 'Dry', 1),
(51, 'Sprite', 'liters', 15.0, 'Dry', 1),
(52, 'Fanta', 'liters', 15.0, 'Dry', 1),
(53, 'Iced tea', 'liters', 10.0, 'Dry', 1),
(54, 'Orange juice', 'liters', 12.0, 'Refrigerated', 1),
(55, 'Lemonade', 'liters', 9.0, 'Refrigerated', 1),
(56, 'Espresso shot', 'units', 1000, 'Dry', 1),
(57, 'Red Bull', 'liters', 16.0, 'Dry', 1),
(58, 'Sparkling water', 'liters', 10.0, 'Dry', 1),
(59, 'Still water', 'liters', 15.0, 'Dry', 1),
(60, 'Vanilla ice cream', 'liters', 15.0, 'Frozen', 1),
(61, 'Chocolate ice cream', 'liters', 15.0, 'Frozen', 1),
(62, 'Strawberry ice cream', 'liters', 15.0, 'Frozen', 1),
(63, 'Oreo pieces', 'kg', 11.0, 'Dry', 1),
(64, 'Whipped cream', 'liters', 12.0, 'Refrigerated', 1),
(65, 'Chocolate syrup', 'liters', 12.5, 'Dry', 1),
(66, 'Brownie chunks', 'kg', 12.0, 'Dry', 1),
(67, 'Nuts', 'kg', 13.0, 'Dry', 1),
(68, 'Cherries', 'kg', 12.0, 'Refrigerated', 1),
(69, 'Banana slices', 'kg', 12.0, 'Refrigerated', 1),
(70, 'Maple syrup', 'liters', 12.0, 'Dry', 1),
(71, 'Waffle cone', 'units', 30, 'Dry', 1),
(72, 'Cookie dough', 'kg', 13.0, 'Frozen', 1),
(73, 'Marshmallows', 'kg', 11.5, 'Dry', 1),
(74, 'Gummy bears', 'kg', 12.0, 'Dry', 1),
(75, 'Raspberry sauce', 'liters', 11.5, 'Refrigerated', 1),
(76, 'Strawberry syrup', 'liters', 11.5, 'Dry', 1),
(77, 'Graham crackers', 'kg', 11.5, 'Dry', 1),
(78, 'Peanut butter', 'kg', 12.0, 'Dry', 1),
(79, 'Almond milk', 'liters', 15.0, 'Refrigerated', 1),
(80, 'Coconut milk', 'liters', 15.0, 'Refrigerated', 1),
(81, 'Milkshake base', 'liters', 16.0, 'Refrigerated', 1),
(82, 'Vanilla extract', 'liters', 11.0, 'Dry', 1),
(83, 'Butter', 'kg', 13.0, 'Refrigerated', 1),
(84, 'Cinnamon', 'kg', 11.0, 'Dry', 1),
(85, 'Ice cubes', 'kg', 10.0, 'Frozen', 1),
(86, 'Cream cheese', 'kg', 13.0, 'Refrigerated', 1),
(87, 'Parmesan cheese', 'kg', 9.9, 'Dry', 1),
(88, 'Greek yogurt', 'kg', 14.0, 'Refrigerated', 1),
(89, 'Chocolate chips', 'kg', 12.0, 'Dry', 1),
(90, 'Vanilla syrup', 'liters', 10.5, 'Dry', 1),
(91, 'Pine nuts', 'kg', 10.0, 'Dry', 1),
(92, 'Basil', 'kg', 10.5, 'Refrigerated', 1),
(93, 'Mint leaves', 'kg', 10.5, 'Refrigerated', 1),
(94, 'Ginger', 'kg', 7.0, 'Dry', 1),
(95, 'Coconut flakes', 'kg', 11.0, 'Dry', 1),
(96, 'Hazelnut spread', 'kg', 12.0, 'Dry', 1),
(97, 'Blueberries', 'kg', 12.0, 'Refrigerated', 1),
(98, 'Raspberries', 'kg', 15.0, 'Refrigerated', 1),
(99, 'Carrot', 'kg', 15.0, 'Refrigerated', 1),
(100, 'Zucchini', 'kg', 17.0, 'Refrigerated', 1);



INSERT INTO Productos (ID_Producto, ID_Oferta, ID_Categoria, Nombre, Precio, Descripcion) VALUES
(1, 1, 1, 'Classic Burger', 7.99, 'Beef burger with lettuce, tomato, and cheese.'),
(2, NULL, 1, 'Display Flex', 8.99, 'Beef burger with cheddar cheese, tomato, onion and a smooth tomato sauce.'),
(3, 5, 1, 'BBQ Classic Burger', 7.49, 'Beef burger with lettuce, tomato, cheese, bacon and a tasty bbq sauce.'),
(4, 4, 1, 'Maneater', 7.19, 'Three Beef burger with lettuce, tomato, cheese, bacon, pickle and moustard.'),
(5, 3, 1, 'Silencer', 8.19, 'Beef burger with lettuce, tomato, cheese, bacon, pickle and mayo.'),
(6, 2, 1, 'Eco-logic Burger', 8.49, 'Beef or vegan burger with tomato, lettuce, cheddar vegan cheese, pickles, onion and avocado sauce.'),
(7, NULL, 1, 'Classic Burger', 7.99, 'Beef burger with lettuce, tomato, and cheese'),
(8, NULL, 1, 'Cheese Explosion', 8.49, 'Juicy beef patty with double cheddar, mozzarella, and creamy cheese sauce'),
(9, NULL, 1, 'BBQ Bacon Burger', 8.99, 'Smoky BBQ sauce, crispy bacon, cheddar, and onion rings'),
(10, NULL, 1, 'Spicy Jalapeño Burger', 8.59, 'Spicy burger with jalapeños, pepper jack cheese, and chipotle mayo'),
(11, NULL, 1, 'Veggie Delight', 7.49, 'Grilled veggie patty with lettuce, tomato, avocado, and vegan aioli'),
(12, NULL, 1, 'Mushroom Swiss Burger', 8.29, 'Beef patty with sautéed mushrooms, Swiss cheese, and garlic mayo'),
(13, NULL, 1, 'Double Trouble', 9.99, 'Two beef patties, double cheddar, bacon, pickles, and house sauce'),
(14, NULL, 2, 'Water', 1.00, 'Bottled still water'),
(15, NULL, 2, 'Cola', 1.50, 'Classic carbonated cola-flavored soft drink'),
(16, NULL, 2, 'Pepsi', 1.30, 'Refreshing cola-flavored soft drink alternative'),
(17, NULL, 2, 'Sprite', 1.40, 'Lemon-lime flavored soda, crisp and caffeine-free'),
(18, NULL, 2, 'Fanta', 1.50, 'Orange-flavored soft drink with fruity taste'),
(19, NULL, 2, 'Iced Tea', 1.20, 'Chilled sweetened tea with a hint of lemon'),
(20, NULL, 2, 'Orange Juice', 1.50, '100% pure squeezed orange juice'),
(21, NULL, 2, 'Red Bull Energy Drink', 1.80, 'Carbonated energy drink with caffeine and vitamins'),
(22, NULL, 2, 'Sparkling Water', 1.20, 'Carbonated mineral water'),
(23, NULL, 2, 'Coffee', 1.60, 'Freshly brewed hot coffee'),
(24, NULL, 3, 'Fries', 2.99, 'Classic crispy fries.'),
(25, NULL, 3, 'Sweet Potato Fries', 3.49, 'Sweet and savory sweet potato fries.'),
(26, NULL, 3, 'Onion Rings', 3.29, 'Crispy golden fried onion rings.'),
(27, NULL, 3, 'Coleslaw', 2.49, 'Creamy cabbage and carrot coleslaw.'),
(28, NULL, 3, 'Mozzarella Sticks', 4.49, 'Fried mozzarella sticks with marinara dip.'),
(29, NULL, 4, 'Vanilla Milkshake', 3.99, 'Creamy vanilla milkshake.'),
(30, NULL, 4, 'Chocolate Brownie', 3.49, 'Warm chocolate brownie with chunks.'),
(31, NULL, 4, 'Oreo Sundae', 4.99, 'Ice cream sundae topped with Oreo pieces.'),
(32, NULL, 4, 'Banana Split', 5.49, 'Classic banana split with three scoops.'),
(33, NULL, 4, 'Strawberry Cheesecake Cup', 4.29, 'Cream cheese and strawberry swirl dessert.');

INSERT INTO Productos_Ingredientes (ID_Ingrediente, ID_Producto, Cantidad) VALUES
-- Classic Burger (ID 1)
(1, 1, 0.15),
(2, 1, 0.02),
(5, 1, 0.03),
(4, 1, 0.03),
(3, 1, 1),

-- Display Flex (ID 2)
(1, 2, 0.15),
(5, 2, 0.03),
(6, 2, 0.02),
(4, 2, 0.04),
(11, 2, 0.01),
(3, 2, 1),

-- BBQ Classic Burger (ID 3)
(1, 3, 0.15),
(2, 3, 0.02),
(5, 3, 0.03),
(4, 3, 0.03),
(8, 3, 0.03),
(13, 3, 0.015),
(3, 3, 1),

-- Maneater (ID 4)
(1, 4, 0.45),
(2, 4, 0.02),
(5, 4, 0.03),
(4, 4, 0.03),
(8, 4, 0.03),
(7, 4, 0.015),
(15, 4, 0.01),
(3, 4, 1),

-- Silencer (ID 5)
(1, 5, 0.15),
(2, 5, 0.02),
(5, 5, 0.03),
(4, 5, 0.03),
(8, 5, 0.03),
(7, 5, 0.015),
(12, 5, 0.01),
(3, 5, 1),

-- Eco-logic Burger (ID 6)
(1, 6, 0.15), -- Se puede sustituir por (16, 6, 0.15) si es versión vegana
(2, 6, 0.02),
(5, 6, 0.03),
(4, 6, 0.03),
(7, 6, 0.015),
(6, 6, 0.02),
(20, 6, 0.02),
(3, 6, 1),

-- Classic Burger (ID 7)
(1, 7, 0.15),
(2, 7, 0.02),
(5, 7, 0.03),
(4, 7, 0.03),
(3, 7, 1),

-- Cheese Explosion (ID 8)
(1, 8, 0.15),
(4, 8, 0.04),
(18, 8, 0.04), -- Mozzarella
(3, 8, 1),

-- BBQ Bacon Burger (ID 9)
(1, 9, 0.15),
(8, 9, 0.03),
(4, 9, 0.03),
(13, 9, 0.02),
(32, 9, 1),
(3, 9, 1),

-- Spicy Jalapeño Burger (ID 10)
(1, 10, 0.15),
(19, 10, 0.02),
(4, 10, 0.03),
(41, 10, 0.015),
(3, 10, 1),

-- Veggie Delight (ID 11)
(16, 11, 0.15),
(2, 11, 0.02),
(5, 11, 0.03),
(20, 11, 0.025),
(42, 11, 0.015),
(3, 11, 1),

-- Mushroom Swiss Burger (ID 12)
(1, 12, 0.15),
(31, 12, 0.03),
(26, 12, 0.03),
(42, 12, 0.015),
(3, 12, 1),

-- Double Trouble (ID 13)
(1, 13, 0.30),
(4, 13, 0.05),
(8, 13, 0.03),
(7, 13, 0.02),
(3, 13, 1),
(11, 13, 0.01),

-- Sides
(33, 24, 0.15), 
(34, 25, 0.15), 
(32, 26, 5),
(35, 27, 0.12),
(18, 28, 0.08),

-- Desserts
(81, 29, 0.2), 
(82, 29, 0.01),
(66, 30, 0.1), 
(89, 30, 0.02),
(60, 31, 0.15),
(63, 31, 0.03),
(64, 31, 0.02),
(60, 32, 0.1),
(61, 32, 0.1),
(62, 32, 0.1),
(69, 32, 0.05),
(68, 32, 0.02),
(86, 33, 0.08),
(98, 33, 0.03),
(64, 33, 0.01);

INSERT INTO Proveedores (ID_Proveedor, nombreEmpresa, Telefono, Email) VALUES
(1, 'James Baxter & Son.', '01524 410910', 'james.baxter@baxterspottedshrimps.co.uk'),
(2, 'Sysco', '281-584-1390', 'SyscoAppSupport@sysco.com'),
(3, 'Unilever Food Solutions', '0800 783 3728', 'info@ufs.com'),
(4, 'Gordon Food Service', '800-968-7500', 'support@gfs.com'),
(5, 'US Foods', '847-720-8000', 'customer.service@usfoods.com');

INSERT INTO Proveedores_Ingredientes (ID_ProveedorIngrediente, ID_Proveedor, ID_Ingrediente, precioUnitario, tiempoEntregaDias) VALUES
(1, 1, 1, 5.0, 2),
(2, 2, 3, 0.5, 1);

INSERT INTO Almacen (ID_Almacen, Nombre, Capacidad, Ubicacion) VALUES
(1, 'Almacén Central', 500, 'Calle Falsa 123');

INSERT INTO Restaurante (ID_Restaurante, Nombre, Direccion, Telefono, Email, Aforo, imagenRestaurante) VALUES
(1, 'Santa Monica', '1501 Ocean Ave, Santa Monica, CA 90401', '213-204-0544', 'santamonica@m&g.com', 68, '/Proyecto/Frontend/Web/src/products/backgrounds/santa-monica-restaurant.png'),
(2, 'Downtown', '622-612 Broadway, CA 90014', '213-203-6233', 'downtown@m&g.com', 42, '/Proyecto/Frontend/Web/src/products/backgrounds/downtown-restaurant.png'),
(3, 'Silver Lake', '306 Virgil Ave, CA 90004', '213-202-2575
', 'silverlake@m&g.com', 77, '/Proyecto/Frontend/Web/src/products/backgrounds/silver-lake-restaurant.png'),
(4, 'West Hollywood', '855-801 N W Knoll Dr, West Hollywood, CA 90069', '213-201-4912
', 'westhollywood@m&g.com', 70, '/Proyecto/Frontend/Web/src/products/backgrounds/west-hollywood-restaurant.png'),
(5, 'Culver City', '6000 Sepulveda Blvd, Culver City, CA 90230', '213-200-4076', 'culvercity@m&g.com', 54, '/Proyecto/Frontend/Web/src/products/backgrounds/culver-city-restaurant.png');

INSERT INTO usuarios (ID_Usuario, Nombre, Email, Contrasena, DNI, Telefono, Direccion) VALUES (5, 'Test User', 'test@mail.com', '1234', '2222', '437534344', 'Calle de la almendrilla');

INSERT INTO Empleados (ID_Empleado, ID_Restaurante, Nombre, Apellidos, DNI, Telefono, Sueldo, fechaContratacion) VALUES
(1, 1, 'Anastasia', 'Gómez Pérez', '12345678L', '600112233', 1200.00, '2023-04-01');

=======

USE esquema_reto;

INSERT INTO Categorias (ID_Categoria, Nombre) VALUES
(1, 'Burger'),
(2, 'Drink'),
(3, 'Sides'),
(4, 'Dessert');

INSERT INTO Ofertas (ID_Oferta, Nombre, Precio, descripcion, columnaImagen, fechaExpiracion) VALUES
(1, 'Classic for 7$', 9.99, 'Enjoy the classic flavor with a special 20% discount. An offer you can’t resist!', '/Proyecto/Frontend/Web/src/products/promos/classic-burger-promo.png','2025-05-31'),
(2, 'Ecologic 10%', 3.50, 'Eco-friendly burger made with fresh and natural ingredients. Healthy and delicious.', '/Proyecto/Frontend/Web/src/products/promos/ecologic-burger-promo.png','2025-07-31'),
(3, 'Silencer 50%', 3.50, 'The quietest but mightiest deal: 50% off on this tasty choice!', '/Proyecto/Frontend/Web/src/products/promos/silencer-burger-promo.png','2025-05-31'),
(4, 'Maneater 20%', 9.99, 'A hearty burger with a 20% discount. Perfect for those who love bold flavors.', '/Proyecto/Frontend/Web/src/products/promos/maneater-burger-promo.png','2025-06-30'),
(5, 'BBQ Classic 50%', 9.99, 'Classic BBQ with a juicy smoky twist and 50% off. Don’t miss out!', '/Proyecto/Frontend/Web/src/products/promos/bbq-burger-promo.png','2025-06-30');

INSERT INTO Ingredientes (ID_Ingrediente, Nombre, unidadMedida, stockDisponible, tipoAlmacenamiento, EstaActivo) VALUES
(1, 'Beef patty', 'kg', 50.0, 'Refrigerated', 1),
(2, 'Lettuce', 'kg', 20.0, 'Refrigerated', 1),
(3, 'Burger bun', 'units', 100, 'Dry', 1),
(4, 'Cheddar cheese', 'kg', 15.0, 'Refrigerated', 1),
(5, 'Tomato', 'kg', 10.0, 'Refrigerated', 1),
(6, 'Onion', 'kg', 10.0, 'Dry', 1),
(7, 'Pickles', 'kg', 18.0, 'Refrigerated', 1),
(8, 'Bacon', 'kg', 12.0, 'Refrigerated', 1),
(9, 'Chicken breast', 'kg', 18.0, 'Refrigerated', 1),
(10, 'Spicy sauce', 'liters', 15.0, 'Dry', 1),
(11, 'Ketchup', 'liters', 10.0, 'Dry', 1),
(12, 'Mayonnaise', 'liters', 17.0, 'Refrigerated', 1),
(13, 'Barbecue sauce', 'liters', 16.0, 'Dry', 1),
(14, 'Egg', 'units', 40, 'Refrigerated', 1),
(15, 'Mustard', 'liters', 15.0, 'Dry', 1),
(16, 'Vegan patty', 'kg', 12.0, 'Frozen', 1),
(17, 'Tofu', 'kg', 10.0, 'Refrigerated', 1),
(18, 'Mozzarella cheese', 'kg', 10.0, 'Refrigerated', 1),
(19, 'Jalapeños', 'kg', 16.0, 'Refrigerated', 1),
(20, 'Avocado', 'kg', 18.0, 'Refrigerated', 1),
(21, 'Pineapple', 'kg', 15.0, 'Refrigerated', 1),
(22, 'Chorizo', 'kg', 16.0, 'Refrigerated', 1),
(23, 'Ciabatta bun', 'units', 50, 'Dry', 1),
(24, 'Whole grain bun', 'units', 60, 'Dry', 1),
(25, 'Gluten-free bun', 'units', 30, 'Dry', 1),
(26, 'Grilled onions', 'kg', 14.0, 'Refrigerated', 1),
(27, 'Caramelized onions', 'kg', 14.0, 'Refrigerated', 1),
(28, 'Cucumber', 'kg', 15.0, 'Refrigerated', 1),
(29, 'Spinach', 'kg', 14.0, 'Refrigerated', 1),
(30, 'Blue cheese', 'kg', 13.0, 'Refrigerated', 1),
(31, 'Swiss cheese', 'kg', 16.0, 'Refrigerated', 1),
(32, 'Fried onion rings', 'units', 50, 'Frozen', 1),
(33, 'French fries', 'kg', 25.0, 'Frozen', 1),
(34, 'Sweet potato fries', 'kg', 10.0, 'Frozen', 1),
(35, 'Coleslaw', 'kg', 18.0, 'Refrigerated', 1),
(36, 'Beetroot', 'kg', 13.0, 'Refrigerated', 1),
(37, 'Red pepper', 'kg', 14.0, 'Refrigerated', 1),
(38, 'Green pepper', 'kg', 14.0, 'Refrigerated', 1),
(39, 'Iceberg lettuce', 'kg', 16.0, 'Refrigerated', 1),
(40, 'Arugula', 'kg', 14.0, 'Refrigerated', 1),
(41, 'Chili sauce', 'liters', 12.0, 'Dry', 1),
(42, 'Garlic aioli', 'liters', 13.0, 'Refrigerated', 1),
(43, 'Honey mustard', 'liters', 12.5, 'Dry', 1),
(44, 'Truffle mayo', 'liters', 11.5, 'Refrigerated', 1),
(45, 'Pastrami', 'kg', 15.0, 'Refrigerated', 1),
(46, 'Crispy onions', 'kg', 13.0, 'Dry', 1),
(47, 'Apple slices', 'kg', 12.0, 'Refrigerated', 1),
(48, 'Sun-dried tomatoes', 'kg', 12.0, 'Dry', 1),
(49, 'Cola', 'liters', 20.0, 'Dry', 1),
(50, 'Pepsi', 'liters', 15.0, 'Dry', 1),
(51, 'Sprite', 'liters', 15.0, 'Dry', 1),
(52, 'Fanta', 'liters', 15.0, 'Dry', 1),
(53, 'Iced tea', 'liters', 10.0, 'Dry', 1),
(54, 'Orange juice', 'liters', 12.0, 'Refrigerated', 1),
(55, 'Lemonade', 'liters', 9.0, 'Refrigerated', 1),
(56, 'Espresso shot', 'units', 1000, 'Dry', 1),
(57, 'Red Bull', 'liters', 16.0, 'Dry', 1),
(58, 'Sparkling water', 'liters', 10.0, 'Dry', 1),
(59, 'Still water', 'liters', 15.0, 'Dry', 1),
(60, 'Vanilla ice cream', 'liters', 15.0, 'Frozen', 1),
(61, 'Chocolate ice cream', 'liters', 15.0, 'Frozen', 1),
(62, 'Strawberry ice cream', 'liters', 15.0, 'Frozen', 1),
(63, 'Oreo pieces', 'kg', 11.0, 'Dry', 1),
(64, 'Whipped cream', 'liters', 12.0, 'Refrigerated', 1),
(65, 'Chocolate syrup', 'liters', 12.5, 'Dry', 1),
(66, 'Brownie chunks', 'kg', 12.0, 'Dry', 1),
(67, 'Nuts', 'kg', 13.0, 'Dry', 1),
(68, 'Cherries', 'kg', 12.0, 'Refrigerated', 1),
(69, 'Banana slices', 'kg', 12.0, 'Refrigerated', 1),
(70, 'Maple syrup', 'liters', 12.0, 'Dry', 1),
(71, 'Waffle cone', 'units', 30, 'Dry', 1),
(72, 'Cookie dough', 'kg', 13.0, 'Frozen', 1),
(73, 'Marshmallows', 'kg', 11.5, 'Dry', 1),
(74, 'Gummy bears', 'kg', 12.0, 'Dry', 1),
(75, 'Raspberry sauce', 'liters', 11.5, 'Refrigerated', 1),
(76, 'Strawberry syrup', 'liters', 11.5, 'Dry', 1),
(77, 'Graham crackers', 'kg', 11.5, 'Dry', 1),
(78, 'Peanut butter', 'kg', 12.0, 'Dry', 1),
(79, 'Almond milk', 'liters', 15.0, 'Refrigerated', 1),
(80, 'Coconut milk', 'liters', 15.0, 'Refrigerated', 1),
(81, 'Milkshake base', 'liters', 16.0, 'Refrigerated', 1),
(82, 'Vanilla extract', 'liters', 11.0, 'Dry', 1),
(83, 'Butter', 'kg', 13.0, 'Refrigerated', 1),
(84, 'Cinnamon', 'kg', 11.0, 'Dry', 1),
(85, 'Ice cubes', 'kg', 10.0, 'Frozen', 1),
(86, 'Cream cheese', 'kg', 13.0, 'Refrigerated', 1),
(87, 'Parmesan cheese', 'kg', 9.9, 'Dry', 1),
(88, 'Greek yogurt', 'kg', 14.0, 'Refrigerated', 1),
(89, 'Chocolate chips', 'kg', 12.0, 'Dry', 1),
(90, 'Vanilla syrup', 'liters', 10.5, 'Dry', 1),
(91, 'Pine nuts', 'kg', 10.0, 'Dry', 1),
(92, 'Basil', 'kg', 10.5, 'Refrigerated', 1),
(93, 'Mint leaves', 'kg', 10.5, 'Refrigerated', 1),
(94, 'Ginger', 'kg', 7.0, 'Dry', 1),
(95, 'Coconut flakes', 'kg', 11.0, 'Dry', 1),
(96, 'Hazelnut spread', 'kg', 12.0, 'Dry', 1),
(97, 'Blueberries', 'kg', 12.0, 'Refrigerated', 1),
(98, 'Raspberries', 'kg', 15.0, 'Refrigerated', 1),
(99, 'Carrot', 'kg', 15.0, 'Refrigerated', 1),
(100, 'Zucchini', 'kg', 17.0, 'Refrigerated', 1);



INSERT INTO Productos (ID_Producto, ID_Oferta, ID_Categoria, Nombre, Precio, Descripcion) VALUES
(1, 1, 1, 'Classic Burger', 7.99, 'Beef burger with lettuce, tomato, and cheese.'),
(2, NULL, 1, 'Display Flex', 8.99, 'Beef burger with cheddar cheese, tomato, onion and a smooth tomato sauce.'),
(3, 5, 1, 'BBQ Classic Burger', 7.49, 'Beef burger with lettuce, tomato, cheese, bacon and a tasty bbq sauce.'),
(4, 4, 1, 'Maneater', 7.19, 'Three Beef burger with lettuce, tomato, cheese, bacon, pickle and moustard.'),
(5, 3, 1, 'Silencer', 8.19, 'Beef burger with lettuce, tomato, cheese, bacon, pickle and mayo.'),
(6, 2, 1, 'Eco-logic Burger', 8.49, 'Beef or vegan burger with tomato, lettuce, cheddar vegan cheese, pickles, onion and avocado sauce.'),
(7, NULL, 1, 'Classic Burger', 7.99, 'Beef burger with lettuce, tomato, and cheese'),
(8, NULL, 1, 'Cheese Explosion', 8.49, 'Juicy beef patty with double cheddar, mozzarella, and creamy cheese sauce'),
(9, NULL, 1, 'BBQ Bacon Burger', 8.99, 'Smoky BBQ sauce, crispy bacon, cheddar, and onion rings'),
(10, NULL, 1, 'Spicy Jalapeño Burger', 8.59, 'Spicy burger with jalapeños, pepper jack cheese, and chipotle mayo'),
(11, NULL, 1, 'Veggie Delight', 7.49, 'Grilled veggie patty with lettuce, tomato, avocado, and vegan aioli'),
(12, NULL, 1, 'Mushroom Swiss Burger', 8.29, 'Beef patty with sautéed mushrooms, Swiss cheese, and garlic mayo'),
(13, NULL, 1, 'Double Trouble', 9.99, 'Two beef patties, double cheddar, bacon, pickles, and house sauce'),
(14, NULL, 2, 'Water', 1.00, 'Bottled still water'),
(15, NULL, 2, 'Cola', 1.50, 'Classic carbonated cola-flavored soft drink'),
(16, NULL, 2, 'Pepsi', 1.30, 'Refreshing cola-flavored soft drink alternative'),
(17, NULL, 2, 'Sprite', 1.40, 'Lemon-lime flavored soda, crisp and caffeine-free'),
(18, NULL, 2, 'Fanta', 1.50, 'Orange-flavored soft drink with fruity taste'),
(19, NULL, 2, 'Iced Tea', 1.20, 'Chilled sweetened tea with a hint of lemon'),
(20, NULL, 2, 'Orange Juice', 1.50, '100% pure squeezed orange juice'),
(21, NULL, 2, 'Red Bull Energy Drink', 1.80, 'Carbonated energy drink with caffeine and vitamins'),
(22, NULL, 2, 'Sparkling Water', 1.20, 'Carbonated mineral water'),
(23, NULL, 2, 'Coffee', 1.60, 'Freshly brewed hot coffee'),
(24, NULL, 3, 'Fries', 2.99, 'Classic crispy fries.'),
(25, NULL, 3, 'Sweet Potato Fries', 3.49, 'Sweet and savory sweet potato fries.'),
(26, NULL, 3, 'Onion Rings', 3.29, 'Crispy golden fried onion rings.'),
(27, NULL, 3, 'Coleslaw', 2.49, 'Creamy cabbage and carrot coleslaw.'),
(28, NULL, 3, 'Mozzarella Sticks', 4.49, 'Fried mozzarella sticks with marinara dip.'),
(29, NULL, 4, 'Vanilla Milkshake', 3.99, 'Creamy vanilla milkshake.'),
(30, NULL, 4, 'Chocolate Brownie', 3.49, 'Warm chocolate brownie with chunks.'),
(31, NULL, 4, 'Oreo Sundae', 4.99, 'Ice cream sundae topped with Oreo pieces.'),
(32, NULL, 4, 'Banana Split', 5.49, 'Classic banana split with three scoops.'),
(33, NULL, 4, 'Strawberry Cheesecake Cup', 4.29, 'Cream cheese and strawberry swirl dessert.');

INSERT INTO Productos_Ingredientes (ID_Ingrediente, ID_Producto, Cantidad) VALUES
-- Classic Burger (ID 1)
(1, 1, 0.15),
(2, 1, 0.02),
(5, 1, 0.03),
(4, 1, 0.03),
(3, 1, 1),

-- Display Flex (ID 2)
(1, 2, 0.15),
(5, 2, 0.03),
(6, 2, 0.02),
(4, 2, 0.04),
(11, 2, 0.01),
(3, 2, 1),

-- BBQ Classic Burger (ID 3)
(1, 3, 0.15),
(2, 3, 0.02),
(5, 3, 0.03),
(4, 3, 0.03),
(8, 3, 0.03),
(13, 3, 0.015),
(3, 3, 1),

-- Maneater (ID 4)
(1, 4, 0.45),
(2, 4, 0.02),
(5, 4, 0.03),
(4, 4, 0.03),
(8, 4, 0.03),
(7, 4, 0.015),
(15, 4, 0.01),
(3, 4, 1),

-- Silencer (ID 5)
(1, 5, 0.15),
(2, 5, 0.02),
(5, 5, 0.03),
(4, 5, 0.03),
(8, 5, 0.03),
(7, 5, 0.015),
(12, 5, 0.01),
(3, 5, 1),

-- Eco-logic Burger (ID 6)
(1, 6, 0.15), -- Se puede sustituir por (16, 6, 0.15) si es versión vegana
(2, 6, 0.02),
(5, 6, 0.03),
(4, 6, 0.03),
(7, 6, 0.015),
(6, 6, 0.02),
(20, 6, 0.02),
(3, 6, 1),

-- Classic Burger (ID 7)
(1, 7, 0.15),
(2, 7, 0.02),
(5, 7, 0.03),
(4, 7, 0.03),
(3, 7, 1),

-- Cheese Explosion (ID 8)
(1, 8, 0.15),
(4, 8, 0.04),
(18, 8, 0.04), -- Mozzarella
(3, 8, 1),

-- BBQ Bacon Burger (ID 9)
(1, 9, 0.15),
(8, 9, 0.03),
(4, 9, 0.03),
(13, 9, 0.02),
(32, 9, 1),
(3, 9, 1),

-- Spicy Jalapeño Burger (ID 10)
(1, 10, 0.15),
(19, 10, 0.02),
(4, 10, 0.03),
(41, 10, 0.015),
(3, 10, 1),

-- Veggie Delight (ID 11)
(16, 11, 0.15),
(2, 11, 0.02),
(5, 11, 0.03),
(20, 11, 0.025),
(42, 11, 0.015),
(3, 11, 1),

-- Mushroom Swiss Burger (ID 12)
(1, 12, 0.15),
(31, 12, 0.03),
(26, 12, 0.03),
(42, 12, 0.015),
(3, 12, 1),

-- Double Trouble (ID 13)
(1, 13, 0.30),
(4, 13, 0.05),
(8, 13, 0.03),
(7, 13, 0.02),
(3, 13, 1),
(11, 13, 0.01),

-- Sides
(33, 24, 0.15), 
(34, 25, 0.15), 
(32, 26, 5),
(35, 27, 0.12),
(18, 28, 0.08),

-- Desserts
(81, 29, 0.2), 
(82, 29, 0.01),
(66, 30, 0.1), 
(89, 30, 0.02),
(60, 31, 0.15),
(63, 31, 0.03),
(64, 31, 0.02),
(60, 32, 0.1),
(61, 32, 0.1),
(62, 32, 0.1),
(69, 32, 0.05),
(68, 32, 0.02),
(86, 33, 0.08),
(98, 33, 0.03),
(64, 33, 0.01);

INSERT INTO Proveedores (ID_Proveedor, nombreEmpresa, Telefono, Email) VALUES
(1, 'James Baxter & Son.', '01524 410910', 'james.baxter@baxterspottedshrimps.co.uk'),
(2, 'Sysco', '281-584-1390', 'SyscoAppSupport@sysco.com'),
(3, 'Unilever Food Solutions', '0800 783 3728', 'info@ufs.com'),
(4, 'Gordon Food Service', '800-968-7500', 'support@gfs.com'),
(5, 'US Foods', '847-720-8000', 'customer.service@usfoods.com');

INSERT INTO Proveedores_Ingredientes (ID_ProveedorIngrediente, ID_Proveedor, ID_Ingrediente, precioUnitario, tiempoEntregaDias) VALUES
(1, 1, 1, 5.0, 2),
(2, 2, 3, 0.5, 1);

INSERT INTO Almacen (ID_Almacen, Nombre, Capacidad, Ubicacion) VALUES
(1, 'Almacén Central', 500, 'Calle Falsa 123');

INSERT INTO Restaurante (ID_Restaurante, Nombre, Direccion, Telefono, Email, Aforo, imagenRestaurante) VALUES
(1, 'Santa Monica', '1501 Ocean Ave, Santa Monica, CA 90401', '213-204-0544', 'santamonica@m&g.com', 68, '/Proyecto/Frontend/Web/src/products/backgrounds/santa-monica-restaurant.png'),
(2, 'Downtown', '622-612 Broadway, CA 90014', '213-203-6233', 'downtown@m&g.com', 42, '/Proyecto/Frontend/Web/src/products/backgrounds/downtown-restaurant.png'),
(3, 'Silver Lake', '306 Virgil Ave, CA 90004', '213-202-2575
', 'silverlake@m&g.com', 77, '/Proyecto/Frontend/Web/src/products/backgrounds/silver-lake-restaurant.png'),
(4, 'West Hollywood', '855-801 N W Knoll Dr, West Hollywood, CA 90069', '213-201-4912
', 'westhollywood@m&g.com', 70, '/Proyecto/Frontend/Web/src/products/backgrounds/west-hollywood-restaurant.png'),
(5, 'Culver City', '6000 Sepulveda Blvd, Culver City, CA 90230', '213-200-4076', 'culvercity@m&g.com', 54, '/Proyecto/Frontend/Web/src/products/backgrounds/culver-city-restaurant.png');

INSERT INTO usuarios (ID_Usuario, Nombre, Email, Contrasena, DNI, Telefono, Direccion) VALUES (5, 'Test User', 'test@mail.com', '1234', '2222', '437534344', 'Calle de la almendrilla');

INSERT INTO Empleados (ID_Empleado, ID_Restaurante, Nombre, Apellidos, DNI, Telefono, Sueldo, fechaContratacion) VALUES
(1, 1, 'Anastasia', 'Gómez Pérez', '12345678L', '600112233', 1200.00, '2023-04-01');

>>>>>>> af3274bb771a8f087d2e1c337210faf1c4ec886c

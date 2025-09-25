# 🛍️ Ecommerce 4 Capas
Este proyecto es una aplicación de **Ecommerce** desarrollada con **Spring Boot** (backend) y **Angular** (frontend), siguiendo la arquitectura de 4 capas.  

---
## 📦 Requisitos previos

Asegúrate de tener instalado lo siguiente:

- **Java 17+**  
- **Maven 3.8+**  
- **Node.js 16+ y npm**  
- **Angular CLI** (`npm install -g @angular/cli`)  
- **Git**  

> ⚡ La base de datos **H2** viene incluida y no requiere configuración inicial.  


## Clonar el repositorio

```powershell
https://github.com/EvelynHuancaMaquera57/Ecommerce--Arquitectura-4-capas-.git
```

## Acceder a la carpeta del proyecto frontend 

Después de clonar el repositorio, accede a la carpeta principal:

```powershell
cd angular/frontend 
```
1. Instala las dependencias:
   
	```powershell
	npm install
	```
2. Instala angular:
	```powershell
    npm install -g @angular/cli@18

3. Confirma instalación:
    ```powershell
	ng version
	```
4. Inicia la aplicación Angular:
   
	```powershell
	ng serve
	```
5. Accede a la aplicación desde tu navegador en `http://localhost:4200`.

---
6. Abre una terminal y navega a la carpeta `spring/demo`.

Después de clonar el repositorio, accede a la carpeta principal:

7. Compilar y ejecutar con Maven
   
	```powershell
	mvn spring-boot:run
	```
3. El backend quedará disponible en:
      ```powershell
	http://localhost:8080
	```
3. Base de datos ingresar a :
	```powershell
	http://localhost:8080/h2-console/
	```
4. Script para la bd :
```powershell
    INSERT INTO products (name, description, price, stock, category, image_url) VALUES 
('iPhone 14', 'Smartphone Apple iPhone 14 128GB', 999.99, 50, 'Electrónicos', 'https://images.unsplash.com/photo-1592750475338-74b7b21085ab?w=400'),
('Samsung Galaxy S23', 'Smartphone Samsung Galaxy S23 256GB', 849.99, 30, 'Electrónicos', 'https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=400'),
('Laptop Dell XPS', 'Laptop Dell XPS 13 pulgadas', 1299.99, 20, 'Electrónicos', 'https://images.unsplash.com/photo-1496181133206-80ce9b88a853?w=400'),
('Camiseta Nike', 'Camiseta deportiva Nike Dri-FIT', 29.99, 100, 'Ropa', 'https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=400'),
('Zapatos Adidas', 'Zapatillas deportivas Adidas Ultraboost', 129.99, 75, 'Ropa', 'https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=400'),
('Sofá 3 plazas', 'Sofá moderno 3 plazas color gris', 599.99, 15, 'Hogar', 'https://images.unsplash.com/photo-1586023492125-27b2c045efd7?w=400'),
('Mesa de centro', 'Mesa de centro de madera maciza', 199.99, 25, 'Hogar', 'https://images.unsplash.com/photo-1555041469-a586c61ea9bc?w=400'),
('Balón de fútbol', 'Balón oficial FIFA tamaño 5', 39.99, 60, 'Deportes', 'https://images.unsplash.com/photo-1614630535140-5c5c8b03a42c?w=400'),
('Ciencia Ficción', 'Libro de ciencia ficción bestseller', 14.99, 200, 'Libros', 'https://images.unsplash.com/photo-1544716278-ca5e3f4abd8c?w=400'),
('Aprendiendo Spring Boot', 'Guía completa de Spring Boot', 29.99, 45, 'Libros', 'https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400');
```
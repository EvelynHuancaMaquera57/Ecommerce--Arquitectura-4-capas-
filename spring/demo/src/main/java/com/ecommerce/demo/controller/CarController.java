
package com.ecommerce.demo.controller;

import com.ecommerce.demo.model.CarItem;
import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.model.User;
import com.ecommerce.demo.service.CarService;
import com.ecommerce.demo.service.ProductService;
import com.ecommerce.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carrito")
@CrossOrigin(origins = "http://localhost:4200")
public class CarController {
    @Autowired
    private CarService carritoService;
    
    @Autowired
    private UserService usuarioService;
    
    @Autowired
    private ProductService productoService;
    
     // Agregar producto al carrito
    @PostMapping("/agregar")
    public ResponseEntity<?> addToCarrito(@RequestBody CarItemRequest request) {
        try {
            Optional<User> usuario = usuarioService.findByEmail(request.getUserEmail());
            Optional<Product> producto = productoService.getProductoById(request.getProductId());
            
            if (usuario.isPresent() && producto.isPresent()) {
                CarItem carItem = carritoService.addToCarrito(usuario.get(), producto.get(), request.getCantidad());
                return ResponseEntity.ok(carItem);
            } else {
                return ResponseEntity.badRequest().body("Usuario o producto no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al agregar al carrito: " + e.getMessage());
        }
    }
    
    // Obtener items del carrito de un usuario
    @GetMapping("/usuario/{userEmail}")
    public ResponseEntity<List<CarItem>> getCarritoByUsuario(@PathVariable String userEmail) {
        Optional<User> usuario = usuarioService.findByEmail(userEmail);
        if (usuario.isPresent()) {
            List<CarItem> carItems = carritoService.getCarritoByUsuario(usuario.get());
            return ResponseEntity.ok(carItems);
        }
        return ResponseEntity.notFound().build();
    }
    
    // Actualizar cantidad
    @PutMapping("/actualizar/{itemId}")
    public ResponseEntity<?> updateCantidad(@PathVariable Long itemId, @RequestBody Integer nuevaCantidad) {
        try {
            CarItem updatedItem = carritoService.updateCantidad(itemId, nuevaCantidad);
            return ResponseEntity.ok(updatedItem);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    // Eliminar item del carrito
    @DeleteMapping("/eliminar/{itemId}")
    public ResponseEntity<?> removeFromCarrito(@PathVariable Long itemId) {
        try {
            carritoService.removeFromCarrito(itemId);
            return ResponseEntity.ok().body("Item eliminado del carrito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar item: " + e.getMessage());
        }
    }
    
    // Vaciar carrito de un usuario
    @DeleteMapping("/vaciar/{userEmail}")
    public ResponseEntity<?> vaciarCarrito(@PathVariable String userEmail) {
        Optional<User> usuario = usuarioService.findByEmail(userEmail);
        if (usuario.isPresent()) {
            carritoService.vaciarCarrito(usuario.get());
            return ResponseEntity.ok().body("Carrito vaciado");
        }
        return ResponseEntity.notFound().build();
    }
    
    // Clase auxiliar para recibir requests
    public static class CarItemRequest {
        private String userEmail;
        private Long productId;
        private Integer cantidad;
        
        // Getters y setters
        public String getUserEmail() { return userEmail; }
        public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
        
        public Long getProductId() { return productId; }
        public void setProductId(Long productId) { this.productId = productId; }
        
        public Integer getCantidad() { return cantidad; }
        public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    }





}
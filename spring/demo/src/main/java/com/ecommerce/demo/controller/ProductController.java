package com.ecommerce.demo.controller;

import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    @Autowired
    private ProductService productoService;
    
    @GetMapping
    public List<Product> getAllProductos() {
        return productoService.getAllProductos();
    }
    
    @GetMapping("/{id}")
    public Optional<Product> getProductoById(@PathVariable Long id) {
        return productoService.getProductoById(id);
    }
    
    @GetMapping("/search")
    public List<Product> searchProductos(@RequestParam String query) {
        return productoService.searchProductos(query);
    }
    
}
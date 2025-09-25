package com.ecommerce.demo.service;

import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productoRepository;
    
    public List<Product> getAllProductos() {
        return productoRepository.findAll();
    }
    
    public Optional<Product> getProductoById(Long id) {
        return productoRepository.findById(id);
    }
    
    public List<Product> searchProductos(String query) {
        return productoRepository.findByNameContainingIgnoreCase(query);
    }
    
    
    public Product saveProducto(Product producto) {
        return productoRepository.save(producto);
    }
    
    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
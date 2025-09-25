package com.ecommerce.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.ecommerce.demo.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
     List<Product> findByNameContainingIgnoreCase(String name);
     
}
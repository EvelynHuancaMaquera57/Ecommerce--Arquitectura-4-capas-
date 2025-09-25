package com.ecommerce.demo.repository;

import com.ecommerce.demo.model.CarItem;
import com.ecommerce.demo.model.User;
import com.ecommerce.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarItemRepository extends JpaRepository<CarItem, Long> {
    List<CarItem> findByUser(User user);
    Optional<CarItem> findByUserAndProduct(User user, Product product);
    void deleteByUser(User user);
}
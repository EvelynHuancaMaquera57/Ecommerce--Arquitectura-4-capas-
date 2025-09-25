package com.ecommerce.demo.service;

import com.ecommerce.demo.model.CarItem;
import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.model.User;
import com.ecommerce.demo.repository.CarItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarItemRepository carItemRepository;
    
     public CarItem addToCarrito(User usuario, Product producto, Integer cantidad) {
        // Verificar si el item ya existe en el carrito
        Optional<CarItem> existingItem = carItemRepository.findByUserAndProduct(usuario, producto);
        
        if (existingItem.isPresent()) {
            // Actualizar cantidad si ya existe
            CarItem item = existingItem.get();
            item.setCantidad(item.getCantidad() + cantidad);
            return carItemRepository.save(item);
        } else {
            // Crear nuevo item
            CarItem newItem = new CarItem();
            newItem.setUser(usuario);
            newItem.setProduct(producto);
            newItem.setCantidad(cantidad);
            return carItemRepository.save(newItem);
        }
    }
    
    public List<CarItem> getCarritoByUsuario(User usuario) {
        return carItemRepository.findByUser(usuario);
    }
    
    public CarItem updateCantidad(Long itemId, Integer nuevaCantidad) {
        Optional<CarItem> itemOptional = carItemRepository.findById(itemId);
        if (itemOptional.isPresent()) {
            CarItem item = itemOptional.get();
            item.setCantidad(nuevaCantidad);
            return carItemRepository.save(item);
        }
        throw new RuntimeException("Item del carrito no encontrado");
    }
    
    public void removeFromCarrito(Long itemId) {
        carItemRepository.deleteById(itemId);
    }
    
    public void vaciarCarrito(User usuario) {
        List<CarItem> items = carItemRepository.findByUser(usuario);
        carItemRepository.deleteAll(items);
    }
   
}
package com.ecommerce.demo.model;

import jakarta.persistence.*; 

@Entity
@Table(name = "carrito_items")
public class CarItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Integer cantidad;
    
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    // Constructores, getters y setters
    public CarItem() {}
  
     public CarItem(Integer cantidad, Product product, User user) {
        this.cantidad = cantidad;
        this.product = product;
        this.user = user;
    }
    
    
    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
 
    
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

     public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

}
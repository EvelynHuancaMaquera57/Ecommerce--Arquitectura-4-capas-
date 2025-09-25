import { Component, OnInit } from '@angular/core';
import { Product } from '../../models/product';
import { ProductService } from '../../services/product.service';
import { CartService } from '../../services/cart.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];
  filteredProducts: Product[] = [];
  searchQuery = '';

  constructor(
    private productService: ProductService,
    private cartService: CartService,
    public authService: AuthService
  ) { }

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.productService.getProducts().subscribe({
      next: (products) => {
        this.products = products;
        this.filteredProducts = products;
      },
      error: (error) => console.error('Error loading products:', error)
    });
  }

  searchProducts(): void {
    if (this.searchQuery.trim()) {
      this.productService.searchProducts(this.searchQuery).subscribe({
        next: (products) => this.filteredProducts = products,
        error: (error) => console.error('Error searching products:', error)
      });
    } else {
      this.filteredProducts = this.products;
    }
  }

  addToCart(product: Product): void {
    this.cartService.addToCart(product, 1).subscribe({
      next: () => {
        alert('Producto agregado al carrito');
      },
      error: (error) => {
        alert('Error al agregar al carrito');
      }
    });
  }

  // AÑADE ESTE MÉTODO NUEVO
  addToCartWithQuantity(product: Product, quantity: number): void {
    if (quantity < 1 || quantity > product.stock) {
      alert('Cantidad inválida');
      return;
    }

    this.cartService.addToCart(product, quantity).subscribe({
      next: () => {
        alert(`${quantity} ${product.name}(s) agregado(s) al carrito`);
      },
      error: (error) => {
        alert('Error al agregar al carrito');
      }
    });
  }


}


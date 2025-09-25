import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { AuthService } from './auth.service';

export interface CartItem {
  id: number;
  cantidad: number;
  product?: any;
}

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private apiUrl = 'http://localhost:8080/api/carrito';
  private cartItems: CartItem[] = [];
  private cartSubject = new BehaviorSubject<CartItem[]>([]);
  
  public cart$ = this.cartSubject.asObservable();

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) { 
    this.loadCartFromStorage();
  }

  // Método temporal - mientras implementas el backend del carrito
  addToCart(product: any, quantity: number = 1): Observable<any> {
    // Por ahora usamos almacenamiento local
    // Cuando implementes los endpoints del carrito en el backend, cambia esto
    
    const newItem: CartItem = {
      id: Date.now(),
      cantidad: quantity,
      product: product
    };
    
    this.cartItems.push(newItem);
    this.saveCartToStorage();
    this.cartSubject.next([...this.cartItems]);
    
    // Retornar un observable simulado
    return new Observable(observer => {
      observer.next(newItem);
      observer.complete();
    });
  }

  removeFromCart(itemId: number): Observable<void> {
    this.cartItems = this.cartItems.filter(item => item.id !== itemId);
    this.saveCartToStorage();
    this.cartSubject.next([...this.cartItems]);
    
    return new Observable(observer => {
      observer.next();
      observer.complete();
    });
  }

  getCartItems(): CartItem[] {
    return [...this.cartItems];
  }

  clearCart(): void {
    this.cartItems = [];
    this.saveCartToStorage();
    this.cartSubject.next([]);
  }

  getTotal(): number {
    return this.cartItems.reduce((total, item) => {
      return total + (item.product?.price || 0) * item.cantidad;
    }, 0);
  }

  getTotalItems(): number {
    return this.cartItems.reduce((total, item) => total + item.cantidad, 0);
  }

  private saveCartToStorage(): void {
    localStorage.setItem('cartItems', JSON.stringify(this.cartItems));
  }

  private loadCartFromStorage(): void {
    const savedCart = localStorage.getItem('cartItems');
    if (savedCart) {
      this.cartItems = JSON.parse(savedCart);
      this.cartSubject.next([...this.cartItems]);
    }
  }
}
import { Component, OnInit } from '@angular/core';
import { CartService } from '../../services/cart.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  isLoggedIn = false;

  constructor(
    public cartService: CartService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.authService.currentUser.subscribe(user => {
      this.isLoggedIn = !!user;
    });
  }

  removeItem(itemId: number): void {
    this.cartService.removeFromCart(itemId);
  }

  checkout(): void {
    if (this.cartService.getCartItems().length === 0) {
      alert('El carrito está vacío');
      return;
    }

    if (!this.isLoggedIn) {
      alert('Por favor inicia sesión para finalizar la compra');
      return;
    }

    alert('¡Gracias por tu compra!');
    this.cartService.clearCart();
  }
}
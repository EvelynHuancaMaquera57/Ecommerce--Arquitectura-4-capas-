import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { CartService } from '../../services/cart.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  isLoggedIn = false;
  cartItemCount = 0;

  constructor(
    public authService: AuthService,
    private cartService: CartService
  ) {
    this.authService.currentUser.subscribe(user => {
      this.isLoggedIn = !!user;
    });

    this.cartService.cart$.subscribe(items => {
      this.cartItemCount = items.reduce((total, item) => total + item.cantidad, 0);
    });
  }

  logout(): void {
    this.authService.logout();
    this.cartService.clearCart();
  }
}
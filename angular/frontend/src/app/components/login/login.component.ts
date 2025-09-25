import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  credentials = {
    email: '',
    password: ''
  };
  errorMessage = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  login(): void {
    this.authService.login(this.credentials).subscribe({
      next: (response) => {
        this.router.navigate(['/products']);
      },
      error: (error) => {
        this.errorMessage = error.error || 'Credenciales incorrectas';
      }
    });
  }
}
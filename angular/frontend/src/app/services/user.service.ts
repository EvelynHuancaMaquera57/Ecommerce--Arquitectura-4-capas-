import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8080/api/usuarios';

  constructor(private http: HttpClient) { }

  // Obtener perfil del usuario actual
  getProfile(): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/profile`);
  }

  // Obtener usuario por ID
  getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/${id}`);
  }

  // Actualizar perfil del usuario (sin incluir password)
  updateProfile(userData: { 
    username: string; 
    email: string; 
    firstName: string; 
    lastName: string; 
    role?: string;
  }): Observable<User> {
    return this.http.put<User>(`${this.apiUrl}/profile`, userData);
  }

  // Cambiar contraseña
  changePassword(passwordData: { 
    currentPassword: string; 
    newPassword: string; 
    confirmPassword: string;
  }): Observable<{ message: string }> {
    return this.http.put<{ message: string }>(`${this.apiUrl}/change-password`, passwordData);
  }

  // Obtener todos los usuarios (para admin)
  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.apiUrl);
  }

  // Actualizar usuario por ID (para admin)
  updateUser(id: number, userData: Partial<User>): Observable<User> {
    return this.http.put<User>(`${this.apiUrl}/${id}`, userData);
  }

  // Eliminar usuario (para admin)
  deleteUser(id: number): Observable<{ message: string }> {
    return this.http.delete<{ message: string }>(`${this.apiUrl}/${id}`);
  }

  // Verificar si el usuario actual es administrador
  isAdmin(): boolean {
    try {
      const user = JSON.parse(localStorage.getItem('currentUser') || '{}');
      return user.role === 'ADMIN' || user.role === 'admin';
    } catch (error) {
      return false;
    }
  }

  // Obtener historial de pedidos del usuario
  getOrderHistory(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/orders/history`);
  }

  // Obtener detalles de un pedido específico
  getOrderDetails(orderId: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/orders/${orderId}`);
  }
}
package com.ecommerce.demo.controller;

import com.ecommerce.demo.model.User;
import com.ecommerce.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    @Autowired
    private UserService usuarioService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User usuario) {
        Optional<User> usuarioOptional = usuarioService.findByEmail(usuario.getEmail());
        
        if (usuarioOptional.isPresent() && usuarioOptional.get().getPassword().equals(usuario.getPassword())) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login exitoso");
            response.put("usuario", usuarioOptional.get());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User usuario) {
        if (usuarioService.existsByEmail(usuario.getEmail())) {
            return ResponseEntity.badRequest().body("El email ya está registrado");
        }
        
        User nuevoUsuario = usuarioService.saveUsuario(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }
}
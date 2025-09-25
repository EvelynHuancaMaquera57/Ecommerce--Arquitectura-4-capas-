package com.ecommerce.demo.service;

import com.ecommerce.demo.model.User;
import com.ecommerce.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository usuarioRepository;
    
    public Optional<User> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
    
    public Boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }
    
    public User saveUsuario(User usuario) {
        return usuarioRepository.save(usuario);
    }
}
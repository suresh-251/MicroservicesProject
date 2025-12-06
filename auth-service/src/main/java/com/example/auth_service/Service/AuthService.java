package com.example.auth_service.Service;

import com.example.auth_service.Config.JwtUtil;
import com.example.auth_service.Entity.AuthRequest;
import com.example.auth_service.Entity.AuthResponse;
import com.example.auth_service.Entity.User;
import com.example.auth_service.Repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepo repository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(UserRepo repository, JwtUtil jwtUtil) {
        this.repository = repository;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponse register(
            AuthRequest request) {
        if (repository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        repository.save(user);

        String token = jwtUtil.generateToken(user.getUsername());
        return new AuthResponse(user.getUsername(), token);
    }

    public AuthResponse login(AuthRequest request) {
        User user = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getUsername());
        return new AuthResponse(user.getUsername(), token);
    }
}

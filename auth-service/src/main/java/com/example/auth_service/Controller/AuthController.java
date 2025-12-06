package com.example.auth_service.Controller;

import com.example.auth_service.Config.JwtUtil;
import com.example.auth_service.Entity.AuthRequest;
import com.example.auth_service.Entity.AuthResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtUtil jwt;
    public AuthController(JwtUtil jwt){this.jwt=jwt;}


    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRequest req){
        // Save user to DB with hashed password
        String token = jwt.generateToken(req.getUsername());
        return new AuthResponse(req.getUsername(), token);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest req){
        String token = jwt.generateToken(req.getUsername());
        return new AuthResponse(req.getUsername(), token);
    }


    @GetMapping("/validate")
    public Map<String,Object> validate(@RequestHeader("Authorization") String auth){
        String token = auth.replace("Bearer ","");
        boolean ok = jwt.validateToken(token);
        return Map.of("valid", ok);
    }
}

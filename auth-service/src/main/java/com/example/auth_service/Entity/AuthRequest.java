package com.example.auth_service.Entity;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
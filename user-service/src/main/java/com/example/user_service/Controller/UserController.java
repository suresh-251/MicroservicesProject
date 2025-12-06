package com.example.user_service.Controller;

import com.example.user_service.Entity.User;
import com.example.user_service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService service;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(service.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.ok(service.createUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(service.getUserById(id).get());
    }



    @PutMapping("/{id}/deduct/{amount}")
    public Double deductBalance(@PathVariable Long id, @PathVariable Double amount){
        return service.deductBalance(id,amount);

    }


    @GetMapping("/{id}/balance")
    public Double userBalance(@PathVariable Long id){
        Double balance = service.userBalance(id).getBody();
        // or throw exception
        return Objects.requireNonNullElse(balance, 0.0);

    }


}

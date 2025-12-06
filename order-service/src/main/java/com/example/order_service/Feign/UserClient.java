package com.example.order_service.Feign;


import com.example.order_service.Config.FeignClientConfig;
import com.example.order_service.Entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Optional;

@FeignClient(name="user-service",configuration = FeignClientConfig.class)
public interface UserClient {

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id);

    @GetMapping("/users/{id}/balance")
    Double getBalance(@PathVariable Long id);

    @PutMapping("/users/{id}/deduct/{amount}")
    void deductBalance(@PathVariable Long id, @PathVariable Double amount);

}

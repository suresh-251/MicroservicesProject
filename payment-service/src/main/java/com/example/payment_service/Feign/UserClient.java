package com.example.payment_service.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name="user-service")
public interface UserClient {
    @GetMapping("/users/{id}/balance")
    Double userBalance(@PathVariable("id") Long id);

    @PutMapping("/users/{id}/deduct/{amount}")
    Double deductBalance(
            @PathVariable("id") Long id,
            @PathVariable("amount")  Double amount
    );
}

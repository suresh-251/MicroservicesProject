package com.example.order_service.Controller;

import com.example.order_service.Entity.Order;
import com.example.order_service.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrderController {

    @Autowired
    OrderService service;
    @PostMapping("/place/{userId}/{productId}/{quantity}")
    public ResponseEntity<?> placeOrder(
            @PathVariable Long userId,
            @PathVariable Long productId,
            @PathVariable Integer quantity ){
        return service.placeOrder(userId,productId,quantity);
    }
}

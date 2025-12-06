package com.example.order_service.Service;

import com.example.order_service.Entity.*;

import com.example.order_service.Feign.PaymentClient;
import com.example.order_service.Feign.ProductClient;
import com.example.order_service.Feign.UserClient;
import com.example.order_service.Repo.OrderRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service

public class OrderService {
    @Autowired
    OrderRepo repository;

    @Autowired
    ProductClient productclient;
    @Autowired
    UserClient userclient;
    @Autowired
    PaymentClient paymentclient;


    public ResponseEntity<?> placeOrder(Long userId, Long productId, Integer quantity) {

        ResponseEntity<User> user = userclient.getUserById(userId);

        if (user==null){
            throw new RuntimeException("User not found with ID: " + userId);
        }

        Product p = productclient.getProductById(productId).getBody();

        if (p == null) {
            throw new RuntimeException("Product not found");
        }

        if (p.getQuantity() < quantity) {
            return ResponseEntity.badRequest().body("Not enough stock");
        }

        Double total = p.getPrice() * quantity;

        // First, reduce stock in ProductService
        ResponseEntity<String> reduceResponse = productclient.reduceQuantity(productId, quantity);
        if (!reduceResponse.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to reduce product stock");
        }

        PaymentRequest req = new PaymentRequest();
        req.setUserId(userId);
        req.setAmount(total);
        PaymentResponse payment=paymentclient.makePayment(req);

        if (!payment.getStatus().equalsIgnoreCase("SUCCESS")) {
            // rollback
            productclient.increaseQuantity(productId, quantity);
            throw new RuntimeException("Payment Failed → Product quantity restored");
        }

        Order order=new Order();
        order.setProductId(productId);
        order.setUserId(userId);
        order.setQuantity(quantity);
        order.setTotalPrice(total);
        order.setStatus("SUCCESS");

        try {
            // Save order in DB
            return ResponseEntity.ok(repository.save(order));
        } catch (Exception e) {
            // Compensate: restore product stock
            order.setStatus("FAILED");
            ResponseEntity<String> increaseResponse =productclient.increaseQuantity(productId, quantity);
            throw new RuntimeException("Order creation failed, stock restored. Reason: " + e.getMessage());
        }

    }
}

package com.example.payment_service.Controller;

import com.example.payment_service.Entity.PaymentRequest;
import com.example.payment_service.Entity.PaymentResponse;
import com.example.payment_service.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    PaymentService service;

    @PostMapping
    public PaymentResponse makePayment(@RequestBody PaymentRequest request) {
        return service.pay(request);
    }

}

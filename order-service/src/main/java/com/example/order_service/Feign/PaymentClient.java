package com.example.order_service.Feign;

import com.example.order_service.Config.FeignClientConfig;
import com.example.order_service.Entity.PaymentRequest;
import com.example.order_service.Entity.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="payment-service",configuration = FeignClientConfig.class)
public interface PaymentClient {

    @PostMapping
    public PaymentResponse makePayment(@RequestBody PaymentRequest request);
}

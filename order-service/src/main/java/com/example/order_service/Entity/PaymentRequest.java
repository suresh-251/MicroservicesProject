package com.example.order_service.Entity;

import lombok.Data;

@Data
public class PaymentRequest {
    private Long userId;
    private Double amount;
}

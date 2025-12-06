package com.example.payment_service.Entity;

import lombok.Data;

@Data
public class PaymentRequest {
    private Long userId;
    private Double amount;
}

package com.example.payment_service.Entity;

import lombok.Data;

@Data
public class UserBalanceResponse   {
    private Long userId;
    private Double balance;
}

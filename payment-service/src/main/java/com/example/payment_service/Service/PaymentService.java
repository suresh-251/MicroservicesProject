package com.example.payment_service.Service;

import com.example.payment_service.Entity.PaymentRequest;
import com.example.payment_service.Entity.PaymentResponse;
import com.example.payment_service.Entity.UserBalanceResponse;
import com.example.payment_service.Feign.UserClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    UserClient userclient;

    public PaymentResponse pay(PaymentRequest request) {
        try {
            // 1. Get user balance
            Double balance = userclient.userBalance(request.getUserId());

            if(balance == null){
                throw new RuntimeException("User-Service returned NULL balance");
            }

            // 2. Check balance
            if (balance < request.getAmount()) {
                return new PaymentResponse("FAILED", "Not enough balance");
            }
            if (request.getAmount() <= 0) {
                throw new IllegalArgumentException("Amount must be greater than zero");
            }


            // 3. Deduct amount
            Double updatedBalance = userclient.deductBalance(request.getUserId(), request.getAmount());

            return new PaymentResponse("SUCCESS", "Payment Completed");

        } catch (FeignException.NotFound e) {
            return new PaymentResponse("FAILED", "User not found");
        } catch (FeignException.BadRequest e) {
            return new PaymentResponse("FAILED", "Invalid request");
        } catch (Exception e) {
            return new PaymentResponse("FAILED", "Payment failed: " + e.getMessage());
        }
    }
}


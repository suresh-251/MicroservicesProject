package com.example.order_service.Feign;

import com.example.order_service.Config.FeignClientConfig;
import com.example.order_service.Entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="product-service",configuration = FeignClientConfig.class)
public interface ProductClient  {

    @GetMapping("products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) ;

    @PutMapping("products/reduce/{productId}/{quantity}")
    public ResponseEntity<String> reduceQuantity(@PathVariable Long productId, @PathVariable Integer quantity);

    @PutMapping("products/increase/{productId}/{quantity}")
    public ResponseEntity<String> increaseQuantity(@PathVariable Long productId, @PathVariable Integer quantity);
}

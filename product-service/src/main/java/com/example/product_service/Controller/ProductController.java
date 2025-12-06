package com.example.product_service.Controller;

import com.example.product_service.Entity.Product;
import com.example.product_service.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    ProductService service;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(service.getAllProducts());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(service.saveProduct(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProductById(id));
    }

    @PutMapping("/reduce/{productId}/{quantity}")
    public ResponseEntity<String> reduceQuantity(@PathVariable Long productId,@PathVariable Integer quantity){
        return service.reduceQuantity(productId,quantity);
    }
    @PutMapping("/increase/{id}/{quantity}")
    public ResponseEntity<String> increaseQuantity(
            @PathVariable Long id,
            @PathVariable Integer quantity) {
       return service.increaseQuantity(id,quantity);
    }
}

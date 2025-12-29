package com.example.product_service.Service;

import com.example.product_service.Entity.Product;
import com.example.product_service.Repo.ProductRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public  Product saveProduct(Product product) {
        return repository.save(product);
    }

    public   Product getProductById(Long id) {
        return repository.findById(id).orElseThrow(()->new RuntimeException("product not found"));
    }


    public ResponseEntity<String> reduceQuantity(
          Long productId,
        Integer quantity) {

        Optional<Product> optionalProduct = repository.findById(productId);
        if (optionalProduct.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found");
        }

        Product product = optionalProduct.get();

        if (product.getQuantity() < quantity) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Not enough stock");
        }

        product.setQuantity(product.getQuantity() - quantity);
        repository.save(product);

        return ResponseEntity.ok("Stock reduced successfully");
    }

    public ResponseEntity<String> increaseQuantity(Long id, Integer quantity) {
        Product product = repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setQuantity(product.getQuantity() + quantity);
        repository.save(product);
        return ResponseEntity.ok("Stock restored");
        }

}

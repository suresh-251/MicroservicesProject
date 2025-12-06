package com.example.user_service.Service;

import com.example.user_service.Entity.User;
import com.example.user_service.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepo repository;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return repository.findById(id);
    }

    public User createUser(User user) {
        return repository.save(user);
    }

    public ResponseEntity<Double> userBalance(Long id) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get().getBalance());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public Double deductBalance(Long id, Double amount) {
        {
            User user = repository.findById(id).get();
            user.setBalance(user.getBalance() - amount);
            repository.save(user);
            return user.getBalance();

        }
    }

}

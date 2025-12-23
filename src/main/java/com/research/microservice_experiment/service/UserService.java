package com.research.microservice_experiment.service;

import com.research.microservice_experiment.model.User;
import com.research.microservice_experiment.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        // Business Logic 1: Check for null
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        // Business Logic 2: Check email format (Simplified)
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }

        // Business Logic 3: Check duplicate email
        Optional<User> existing = userRepository.findByEmail(user.getEmail());
        if (existing.isPresent()) {
            throw new RuntimeException("User already exists");
        }

        // Business Logic 4: Password strength
        if (user.getPassword() == null || user.getPassword().length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters");
        }

        // Default logic
        user.setActive(true);
        user.setRole("USER");

        return userRepository.save(user);
    }
}

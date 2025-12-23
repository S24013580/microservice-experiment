package com.research.microservice_experiment.repository;

import com.research.microservice_experiment.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<User> findByEmail(String email);
    User save(User user);
}

package com.research.microservice_experiment.repository;

import com.research.microservice_experiment.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // We will verify if the AI correctly mocks this method
}
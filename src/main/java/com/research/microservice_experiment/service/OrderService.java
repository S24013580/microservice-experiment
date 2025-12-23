package com.research.microservice_experiment.service;

import com.research.microservice_experiment.model.Order;
import com.research.microservice_experiment.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order processOrder(Order order) {
        // AI Test Target 1: Null check
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        // AI Test Target 2: Business Logic (Negative Amount)
        if (order.getAmount() == null || order.getAmount() <= 0) {
            throw new IllegalArgumentException("Invalid order amount");
        }

        // AI Test Target 3: Complex Logic (Priority Status)
        // If amount > 1000, set priority to true
        if (order.getAmount() > 1000) {
            order.setPriority(true);
        } else {
            order.setPriority(false);
        }

        order.setStatus("COMPLETED");
        return orderRepository.save(order);
    }
}
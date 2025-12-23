package com.research.microservice_experiment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role; // e.g., "USER", "ADMIN"
    private boolean active;
}
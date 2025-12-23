package com.research.microservice_experiment.service;

import com.research.microservice_experiment.model.User;
import com.research.microservice_experiment.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void givenValidUser_whenRegister_thenSuccess() {
        // Arrange
        User newUser = new User(1L, "manoj", "manoj@test.com", "password123", "USER", true);
        when(userRepository.findByEmail("manoj@test.com")).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(newUser);

        // Act
        User created = userService.registerUser(newUser);

        // Assert
        assertNotNull(created);
        assertEquals("USER", created.getRole());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void givenExistingEmail_whenRegister_thenThrowException() {
        // Arrange
        User newUser = new User(null, "manoj", "manoj@test.com", "pass", null, false);
        when(userRepository.findByEmail("manoj@test.com")).thenReturn(Optional.of(new User()));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> userService.registerUser(newUser));
        verify(userRepository, never()).save(any(User.class));
    }
}
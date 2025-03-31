package com.example.icesitrade.service;

import com.example.icesitrade.model.Role;
import com.example.icesitrade.model.User;
import com.example.icesitrade.repository.UserRepository;
import com.example.icesitrade.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void testCreateUser() {
        User user = new User(null, "Nico", "nico@example.com", "1234", new Role(1L, "USER"));
        User savedUser = new User(1L, "Nico", "nico@example.com", "1234", new Role(1L, "USER"));

        when(userRepository.save(user)).thenReturn(savedUser);

        User result = userService.saveUser(user);
        assertEquals(1L, result.getId());
        assertEquals("Nico", result.getName());
    }

    @Test
    void testFindUserById() {
        User user = new User(1L, "Nico", "nico@example.com", "1234", new Role(1L, "USER"));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserById(1L);
        assertTrue(result.isPresent());
        assertEquals("Nico", result.get().getName());
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }
}

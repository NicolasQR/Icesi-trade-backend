package com.example.icesitrade.service;

import com.example.icesitrade.model.Role;
import com.example.icesitrade.model.User;
import com.example.icesitrade.repository.UserRepository;
import com.example.icesitrade.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        User user = User.builder()
                .name("Test User")
                .email("test@example.com")
                .password("1234")
                .role(Role.builder().id(1L).name("USER").build())
                .build();

        when(userRepository.save(any(User.class))).thenReturn(user);

        User saved = userService.saveUser(user);

        assertNotNull(saved);
        assertEquals("Test User", saved.getName());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testGetUserById() {
        User user = new User();
        user.setId(1L);
        user.setName("Nico");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserById(1L);

        assertTrue(result.isPresent());
        assertEquals("Nico", result.get().getName());
    }

    @Test
    void testDeleteUser() {
        Long userId = 1L;
        doNothing().when(userRepository).deleteById(userId);

        userService.deleteUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }
}

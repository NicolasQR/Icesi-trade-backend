package com.example.icesitrade.service.impl;

import com.example.icesitrade.model.User;
import com.example.icesitrade.repository.UserRepository;
import com.example.icesitrade.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(existing -> {
            existing.setName(updatedUser.getName());
            existing.setEmail(updatedUser.getEmail());
            existing.setPassword(updatedUser.getPassword());
            existing.setRole(updatedUser.getRole());
            return userRepository.save(existing);
        }).orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

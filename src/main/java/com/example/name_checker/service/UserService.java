package com.example.name_checker.service;

import com.example.name_checker.dto.UsernameCheckResponse;
import com.example.name_checker.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UsernameCheckResponse checkUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return new UsernameCheckResponse("", false, "Username cannot be empty.");
        }

        String trimmed = username.trim();
        boolean exists = userRepository.existsByUsername(trimmed);

        if (exists) {
            return new UsernameCheckResponse(trimmed, true, "Username '" + trimmed + "' exists in the database.");
        } else {
            return new UsernameCheckResponse(trimmed, false, "Username '" + trimmed + "' does not exist in the database.");
        }
    }

    public boolean registerUser(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        return userRepository.addUser(username.trim());
    }

    public void initializeDatabase() {
        userRepository.initTable();
        if (userRepository.count() == 0) {
            userRepository.addUser("admin");
            userRepository.addUser("john_doe");
            userRepository.addUser("jane_smith");
            userRepository.addUser("alex");
        }
    }
}

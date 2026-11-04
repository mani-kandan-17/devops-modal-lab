package com.example.name_checker.controller;

import com.example.name_checker.dto.UsernameCheckResponse;
import com.example.name_checker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/check")
    public ResponseEntity<UsernameCheckResponse> checkUsername(@RequestParam(name = "username", required = false) String username) {
        if (username == null || username.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(
                    new UsernameCheckResponse("", false, "Username query parameter is required.")
            );
        }

        UsernameCheckResponse response = userService.checkUsername(username);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addUser(@RequestParam(name = "username") String username) {
        if (username == null || username.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "Username cannot be empty."
            ));
        }

        boolean created = userService.registerUser(username);
        if (created) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "success", true,
                    "username", username.trim(),
                    "message", "Username '" + username.trim() + "' was added successfully."
            ));
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                    "success", false,
                    "username", username.trim(),
                    "message", "Username '" + username.trim() + "' already exists or could not be added."
            ));
        }
    }
}

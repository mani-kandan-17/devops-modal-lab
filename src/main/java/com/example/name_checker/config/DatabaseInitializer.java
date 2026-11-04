package com.example.name_checker.config;

import com.example.name_checker.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DatabaseInitializer.class);
    private final UserService userService;

    public DatabaseInitializer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        try {
            log.info("Initializing database table if not present...");
            userService.initializeDatabase();
            log.info("Database table checked/initialized successfully.");
        } catch (Exception e) {
            log.warn("Database initialization deferred: {}. Ensure MySQL is running and application.properties credentials are valid.", e.getMessage());
        }
    }
}

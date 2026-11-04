package com.example.name_checker.dto;

public class UsernameCheckResponse {
    private String username;
    private boolean exists;
    private String message;

    public UsernameCheckResponse() {
    }

    public UsernameCheckResponse(String username, boolean exists, String message) {
        this.username = username;
        this.exists = exists;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

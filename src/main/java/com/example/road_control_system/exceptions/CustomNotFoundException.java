package com.example.road_control_system.exceptions;

public class CustomNotFoundException extends RuntimeException {
    private final String path;

    public CustomNotFoundException(String message, String path) {
        super(message);
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}

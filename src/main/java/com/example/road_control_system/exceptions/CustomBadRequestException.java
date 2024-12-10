package com.example.road_control_system.exceptions;

public class CustomBadRequestException extends RuntimeException {
    private final String path;

    public CustomBadRequestException(String message, String path) {
        super(message);
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}

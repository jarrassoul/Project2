package com.example.road_control_system.exceptions;

public class CustomInternalServerErrorException extends RuntimeException {
    private final String path;

    public CustomInternalServerErrorException(String message, String path) {
        super(message);
        this.path = path;
    }

    public String getPath() {
        return path;
    }
    
}

package org.example;

public class CityProcessingException extends RuntimeException {
    public CityProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
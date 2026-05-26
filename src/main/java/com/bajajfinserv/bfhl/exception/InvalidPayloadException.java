package com.bajajfinserv.bfhl.exception;

/**
 * Exception thrown when the request payload is invalid, null, or empty
 */
public class InvalidPayloadException extends RuntimeException {
    public InvalidPayloadException(String message) {
        super(message);
    }
}

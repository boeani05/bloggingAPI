package com.boeani.bloggingAPI.exceptions;

/**
 * Thrown when a requested post does not exist.
 */
public class PostNotFoundException extends RuntimeException {
    /**
     * Creates a new exception with a custom message.
     *
     * @param message error description
     */
    public PostNotFoundException(String message) {
        super(message);
    }
}

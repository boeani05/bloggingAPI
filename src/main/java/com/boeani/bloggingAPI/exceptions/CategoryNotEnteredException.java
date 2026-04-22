package com.boeani.bloggingAPI.exceptions;

/**
 * Thrown when category is missing in a post request.
 */
public class CategoryNotEnteredException extends RuntimeException {
    /**
     * Creates a new exception with a custom message.
     *
     * @param message error description
     */
    public CategoryNotEnteredException(String message) {
        super(message);
    }
}

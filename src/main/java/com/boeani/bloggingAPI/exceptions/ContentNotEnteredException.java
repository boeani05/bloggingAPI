package com.boeani.bloggingAPI.exceptions;

/**
 * Thrown when content is missing in a post request.
 */
public class ContentNotEnteredException extends RuntimeException {
    /**
     * Creates a new exception with a custom message.
     *
     * @param message error description
     */
    public ContentNotEnteredException(String message) {
        super(message);
    }
}

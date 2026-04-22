package com.boeani.bloggingAPI.exceptions;

/**
 * Thrown when a title is missing in a post request.
 */
public class TitleNotEnteredException extends RuntimeException {
    /**
     * Creates a new exception with a custom message.
     *
     * @param message error description
     */
    public TitleNotEnteredException(String message) {
        super(message);
    }
}

package com.boeani.bloggingAPI.exceptions;

/**
 * Thrown when tags are missing in a post request.
 */
public class TagsNotEnteredException extends RuntimeException {
    /**
     * Creates a new exception with a custom message.
     *
     * @param message error description
     */
    public TagsNotEnteredException(String message) {
        super(message);
    }
}

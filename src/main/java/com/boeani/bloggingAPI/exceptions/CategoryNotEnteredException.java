package com.boeani.bloggingAPI.exceptions;

public class CategoryNotEnteredException extends RuntimeException {
    public CategoryNotEnteredException(String message) {
        super(message);
    }
}

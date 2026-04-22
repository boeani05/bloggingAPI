package com.boeani.bloggingAPI.exceptions;

public class TitleNotEnteredException extends RuntimeException {
    public TitleNotEnteredException(String message) {
        super(message);
    }
}

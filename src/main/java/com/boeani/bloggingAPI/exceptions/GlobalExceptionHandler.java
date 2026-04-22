package com.boeani.bloggingAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

// Handles all Exceptions, that might be thrown throughout the program
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handles exception, when title is not entered on call
    @ExceptionHandler(TitleNotEnteredException.class)
    public ResponseEntity<Map<String, Object>> handleMissingTitle(TitleNotEnteredException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "error", "TitleNotEnteredException",
                "message", exception.getMessage()
        ));
    }

    @ExceptionHandler(ContentNotEnteredException.class)
    public ResponseEntity<Map<String, Object>> handleMissingContent(ContentNotEnteredException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "error", "ContentNotEnteredException",
                "message", exception.getMessage()
        ));
    }

    @ExceptionHandler(CategoryNotEnteredException.class)
    public ResponseEntity<Map<String, Object>> handleMissingCategory(CategoryNotEnteredException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "error", "CategoryNotEnteredException",
                "message", exception.getMessage()
        ));
    }

    @ExceptionHandler(TagsNotEnteredException.class)
    public ResponseEntity<Map<String, Object>> handleMissingTags(TagsNotEnteredException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "error", "TagsNotEnteredException",
                "message", exception.getMessage()
        ));
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handlePostNotFound(PostNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "error", "Post Not Found",
                "message", exception.getMessage()
        ));
    }

}

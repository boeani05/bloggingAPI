package com.boeani.bloggingAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

/**
 * Centralized exception mapping for REST endpoints.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maps missing-title errors to HTTP 400.
     *
     * @param exception thrown validation/business exception
     * @return standardized error response
     */
    @ExceptionHandler(TitleNotEnteredException.class)
    public ResponseEntity<Map<String, Object>> handleMissingTitle(TitleNotEnteredException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "error", "TitleNotEnteredException",
                "message", exception.getMessage()
        ));
    }

    /**
     * Maps missing-content errors to HTTP 400.
     *
     * @param exception thrown validation/business exception
     * @return standardized error response
     */
    @ExceptionHandler(ContentNotEnteredException.class)
    public ResponseEntity<Map<String, Object>> handleMissingContent(ContentNotEnteredException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "error", "ContentNotEnteredException",
                "message", exception.getMessage()
        ));
    }

    /**
     * Maps missing-category errors to HTTP 400.
     *
     * @param exception thrown validation/business exception
     * @return standardized error response
     */
    @ExceptionHandler(CategoryNotEnteredException.class)
    public ResponseEntity<Map<String, Object>> handleMissingCategory(CategoryNotEnteredException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "error", "CategoryNotEnteredException",
                "message", exception.getMessage()
        ));
    }

    /**
     * Maps missing-tags errors to HTTP 400.
     *
     * @param exception thrown validation/business exception
     * @return standardized error response
     */
    @ExceptionHandler(TagsNotEnteredException.class)
    public ResponseEntity<Map<String, Object>> handleMissingTags(TagsNotEnteredException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "error", "TagsNotEnteredException",
                "message", exception.getMessage()
        ));
    }

    /**
     * Maps post-not-found errors to HTTP 404.
     *
     * @param exception thrown not-found exception
     * @return standardized error response
     */
    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handlePostNotFound(PostNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "error", "Post Not Found",
                "message", exception.getMessage()
        ));
    }
}

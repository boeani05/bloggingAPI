package com.boeani.bloggingAPI.controller;

import com.boeani.bloggingAPI.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteController {

    private final PostService postService;

    public DeleteController(PostService postService) {
        this.postService = postService;
    }

    @DeleteMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePostById(
            @PathVariable Long id
    ) {
        postService.deletePostById(id);
    }
}

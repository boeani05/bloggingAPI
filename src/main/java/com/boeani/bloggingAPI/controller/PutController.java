package com.boeani.bloggingAPI.controller;

import com.boeani.bloggingAPI.dto.request.CreatePostRequest;
import com.boeani.bloggingAPI.entity.Post;
import com.boeani.bloggingAPI.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class PutController {

    private final PostService postService;

    public PutController(PostService postService) {
        this.postService = postService;
    }

    @PutMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post updatePost(
            @PathVariable Long id,
            @Valid @RequestBody CreatePostRequest createPostRequest
    ) {
        return postService.updatePost(id, createPostRequest);
    }
}

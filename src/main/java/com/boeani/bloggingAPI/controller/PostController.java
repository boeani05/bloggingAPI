package com.boeani.bloggingAPI.controller;

import com.boeani.bloggingAPI.dto.request.CreatePostRequest;
import com.boeani.bloggingAPI.entity.Post;
import com.boeani.bloggingAPI.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for create-post operations.
 */
@RestController
public class PostController {

    /**
     * Service that handles post-related business operations.
     */
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * Creates a new blog post from the provided request payload.
     * <p>
     * The endpoint responds with HTTP status {@code 201 Created} when the post
     * has been successfully persisted.
     * </p>
     *
     * @param createPostRequest request payload containing the new post data
     * @return the newly created and persisted post
     */
    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(
            @Valid @RequestBody CreatePostRequest createPostRequest
    ) {
        return postService.createPost(createPostRequest);
    }
}

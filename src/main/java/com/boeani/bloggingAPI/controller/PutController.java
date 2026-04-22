package com.boeani.bloggingAPI.controller;

import com.boeani.bloggingAPI.dto.request.CreatePostRequest;
import com.boeani.bloggingAPI.entity.Post;
import com.boeani.bloggingAPI.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for updating existing posts.
 */
@RestController
public class PutController {

    /**
     * Service that performs post update operations.
     */
    private final PostService postService;

    /**
     * Creates a new controller instance.
     *
     * @param postService service used to process update requests
     */
    public PutController(PostService postService) {
        this.postService = postService;
    }

    /**
     * Updates a post by id.
     *
     * @param id identifier of the post to update
     * @param createPostRequest request payload with updated values
     * @return the updated post
     */
    @PutMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post updatePost(
            @PathVariable Long id,
            @Valid @RequestBody CreatePostRequest createPostRequest
    ) {
        return postService.updatePost(id, createPostRequest);
    }
}

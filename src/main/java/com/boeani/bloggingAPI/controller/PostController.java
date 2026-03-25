package com.boeani.bloggingAPI.controller;

import com.boeani.bloggingAPI.dto.request.CreatePostRequest;
import com.boeani.bloggingAPI.entity.Post;
import com.boeani.bloggingAPI.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller that exposes HTTP endpoints for working with {@link Post} resources.
 * <p>
 * Delegates incoming API requests to {@link PostService} in order to retrieve
 * existing posts and create new ones.
 * </p>
 */
@RestController
public class PostController {

    /**
     * Service that handles post-related business operations.
     */
    private final PostService postService;

    /**
     * Creates a new controller instance with the required service dependency.
     *
     * @param postService service used to handle post API requests
     */
    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * Retrieves all blog posts currently available in the system.
     *
     * @return a list of all persisted posts
     */
    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    /**
     * Creates a new blog post from the provided request payload.
     * <p>
     * The endpoint responds with HTTP status {@code 201 Created} when the post
     * has been successfully persisted.
     * </p>
     *
     * @param createPostRequest request body containing the data for the new post
     * @return the newly created and persisted post
     */
    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(
            @RequestBody CreatePostRequest createPostRequest
    ) {
        return postService.createPost(createPostRequest);
    }
}

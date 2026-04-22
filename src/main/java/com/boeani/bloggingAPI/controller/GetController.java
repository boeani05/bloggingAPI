package com.boeani.bloggingAPI.controller;

import com.boeani.bloggingAPI.entity.Post;
import com.boeani.bloggingAPI.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller that exposes HTTP endpoints for working with {@link Post} resources.
 * <p>
 * Delegates incoming API requests to {@link PostService} in order to retrieve
 * existing posts and create new ones.
 * </p>
 */
@RestController
public class GetController {

    /**
     * Service that handles post-related business operations.
     */
    private final PostService postService;

    /**
     * Creates a new controller instance with the required service dependency.
     *
     * @param postService service used to handle post API requests
     */
    public GetController(PostService postService) {
        this.postService = postService;
    }

    /**
     * Retrieves all blog posts currently available in the system.
     *
     * @return a list of all persisted posts
     */
    @GetMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post getPostById(
            @PathVariable Long id
    ) {
        return postService.getPostById(id);
    }
}

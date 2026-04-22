package com.boeani.bloggingAPI.controller;

import com.boeani.bloggingAPI.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for deleting posts.
 */
@RestController
public class DeleteController {

    /**
     * Service that performs delete operations.
     */
    private final PostService postService;

    /**
     * Creates a new controller instance.
     *
     * @param postService service used to process delete requests
     */
    public DeleteController(PostService postService) {
        this.postService = postService;
    }

    /**
     * Deletes a post by id.
     *
     * @param id identifier of the post to delete
     */
    @DeleteMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePostById(
            @PathVariable Long id
    ) {
        postService.deletePostById(id);
    }
}

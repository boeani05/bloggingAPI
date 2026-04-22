package com.boeani.bloggingAPI.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Request payload used to create or update a post.
 */
@Setter
@Getter
public class CreatePostRequest {

    /**
     * Title text of the post.
     */
    @NotEmpty
    private String title;

    /**
     * Main post content.
     */
    @NotEmpty
    private String content;

    /**
     * Category label for grouping posts.
     */
    @NotEmpty
    private String category;

    /**
     * Tags used for filtering and search.
     */
    @NotEmpty
    private List<String> tags;

    /**
     * Creates a new request object with all required post fields.
     *
     * @param title title of the post
     * @param content content of the post
     * @param category category of the post
     * @param tags tags associated with the post
     */
    public CreatePostRequest(String title, String content, String category, List<String> tags) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.tags = tags;
    }

    /**
     * No-args constructor required by Jackson.
     */
    public CreatePostRequest() {
    }

}

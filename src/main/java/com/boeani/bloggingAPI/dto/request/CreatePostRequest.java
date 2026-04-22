package com.boeani.bloggingAPI.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Data transfer object used to create a new blog post.
 * <p>
 * Carries the user-provided input from the API layer to the service layer
 * when a new post is submitted.
 * </p>
 */
@Setter
@Getter
public class CreatePostRequest {

    /**
     * Title of the post to be created.
     * -- GETTER --
     * Returns the title of the post.
     * <p>
     * <p>
     * -- SETTER --
     * Sets the title of the post.
     *
     */
    @NotEmpty
    private String title;

    /**
     * Main body content of the post.
     * -- GETTER --
     * Returns the main content of the post.
     * <p>
     * <p>
     * -- SETTER --
     * Sets the main content of the post.
     *
     */
    @NotEmpty
    private String content;

    /**
     * Category assigned to the post.
     * -- GETTER --
     * Returns the category of the post.
     * <p>
     * <p>
     * -- SETTER --
     * Sets the category of the post.
     *
     */
    @NotEmpty
    private String category;

    /**
     * Optional tags used for classification and search.
     * -- GETTER --
     * Returns the tags associated with the post.
     * <p>
     * <p>
     * -- SETTER --
     * Sets the tags associated with the post.
     *
     */
    @NotEmpty
    private List<String> tags;

    /**
     * Creates a new request object containing all required post data.
     *
     * @param title    title of the post
     * @param content  content/body of the post
     * @param category category of the post
     * @param tags     list of tags associated with the post
     */
    public CreatePostRequest(String title, String content, String category, List<String> tags) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.tags = tags;
    }

    /**
     * Creates an empty request object.
     * <p>
     * Required by serialization/deserialization frameworks (e.g. Jackson)
     * when mapping incoming JSON payloads.
     * </p>
     */
    public CreatePostRequest() {
    }

}

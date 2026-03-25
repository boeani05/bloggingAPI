package com.boeani.bloggingAPI.dto.request;

import java.util.List;

/**
 * Data transfer object used to create a new blog post.
 * <p>
 * Carries the user-provided input from the API layer to the service layer
 * when a new post is submitted.
 * </p>
 */
public class CreatePostRequest {

    /**
     * Title of the post to be created.
     */
    private String title;

    /**
     * Main body content of the post.
     */
    private String content;

    /**
     * Category assigned to the post.
     */
    private String category;

    /**
     * Optional tags used for classification and search.
     */
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

    /**
     * Returns the title of the post.
     *
     * @return the post title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the post.
     *
     * @param title title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the main content of the post.
     *
     * @return the post content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the main content of the post.
     *
     * @param content content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Returns the category of the post.
     *
     * @return the post category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category of the post.
     *
     * @param category category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Returns the tags associated with the post.
     *
     * @return list of post tags
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * Sets the tags associated with the post.
     *
     * @param tags list of tags to set
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}

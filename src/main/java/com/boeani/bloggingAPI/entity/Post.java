package com.boeani.bloggingAPI.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a blog post entity in the system.
 * <p>
 * This entity contains all information related to a blog post, including
 * its content, metadata (title, category, tags), and timestamps for creation
 * and updates. Posts are persisted to the database and can be retrieved,
 * created, updated, and deleted through repository operations.
 * </p>
 *
 * @since 1.0
 */
@Entity
@NoArgsConstructor
public class Post {

    /**
     * Unique identifier for the post.
     * Auto-generated using identity strategy.
     */
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    /**
     * The title of the blog post.
     * Typically a brief, descriptive heading for the post content.
     */
    private String title;

    /**
     * The main content of the blog post.
     * Contains the full text/body of the post.
     */
    private String content;

    /**
     * The category to which this post belongs.
     * Used for organizing and filtering posts by topic.
     */
    private String category;

    /**
     * A list of tags associated with this post.
     * Tags provide additional metadata for categorization and searching.
     */
    @ElementCollection
    @CollectionTable
    private List<String> tags;

    /**
     * The timestamp when the post was originally created.
     * Automatically set upon post creation.
     */
    private LocalDateTime createdAt;

    /**
     * The timestamp of the last update to the post.
     * Updated whenever the post content or metadata is modified.
     */
    private LocalDateTime updatedAt;

    /**
     * Gets the unique identifier of the post.
     *
     * @return the post's ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the post.
     *
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the title of the post.
     *
     * @return the post's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the post.
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the content of the post.
     *
     * @return the post's content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the post.
     *
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the category of the post.
     *
     * @return the post's category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category of the post.
     *
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gets the list of tags associated with the post.
     *
     * @return the list of tags
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * Sets the list of tags for the post.
     *
     * @param tags the tags to set
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * Gets the timestamp when the post was created.
     *
     * @return the creation timestamp
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp for when the post was created.
     *
     * @param createdAt the creation timestamp to set
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the timestamp when the post was last updated.
     *
     * @return the last update timestamp
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the timestamp for when the post was last updated.
     *
     * @param updatedAt the update timestamp to set
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

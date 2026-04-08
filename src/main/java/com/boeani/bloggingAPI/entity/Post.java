package com.boeani.bloggingAPI.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Setter
@Getter
@Entity
@NoArgsConstructor
public class Post {

    /**
     * Unique identifier for the post.
     * Auto-generated using identity strategy.
     * -- GETTER --
     *  Gets the unique identifier of the post.
     * <p>
     *
     * -- SETTER --
     *  Sets the unique identifier of the post.
     *
     */
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    /**
     * The title of the blog post.
     * Typically, a brief, descriptive heading for the post content.
     * -- GETTER --
     *  Gets the title of the post.
     * <p>
     *
     * -- SETTER --
     *  Sets the title of the post.
     *
     */
    private String title;

    /**
     * The main content of the blog post.
     * Contains the full text/body of the post.
     * -- GETTER --
     *  Gets the content of the post.
     * <p>
     *
     * -- SETTER --
     *  Sets the content of the post.
     *
     */
    private String content;

    /**
     * The category to which this post belongs.
     * Used for organizing and filtering posts by topic.
     * -- GETTER --
     *  Gets the category of the post.
     * <p>
     *
     * -- SETTER --
     *  Sets the category of the post.
     *
     */
    private String category;

    /**
     * A list of tags associated with this post.
     * Tags provide additional metadata for categorization and searching.
     * -- GETTER --
     *  Gets the list of tags associated with the post.
     * <p>
     *
     * -- SETTER --
     *  Sets the list of tags for the post.
     *
     */
    @ElementCollection
    @CollectionTable
    private List<String> tags;

    /**
     * The timestamp when the post was originally created.
     * Automatically set upon post creation.
     * -- GETTER --
     *  Gets the timestamp when the post was created.
     * <p>
     *
     * -- SETTER --
     *  Sets the timestamp for when the post was created.
     *
     */
    private LocalDateTime createdAt;

    /**
     * The timestamp of the last update to the post.
     * Updated whenever the post content or metadata is modified.
     * -- GETTER --
     *  Gets the timestamp when the post was last updated.
     * <p>
     *
     * -- SETTER --
     *  Sets the timestamp for when the post was last updated.
     *
     */
    private LocalDateTime updatedAt;

}

package com.boeani.bloggingAPI.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * JPA entity representing a blog post.
 */
@Setter
@Getter
@Entity
@NoArgsConstructor
public class Post {

    /**
     * Primary key of the post.
     */
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    /**
     * Human-readable post title.
     */
    private String title;

    /**
     * Main body text of the post.
     */
    private String content;

    /**
     * Category used for grouping posts.
     */
    private String category;

    /**
     * Keyword tags associated with the post.
     */
    @ElementCollection
    @CollectionTable
    private List<String> tags;

    /**
     * Creation timestamp in server time.
     */
    private LocalDateTime createdAt;

    /**
     * Last modification timestamp in server time.
     */
    private LocalDateTime updatedAt;

}

package com.boeani.bloggingAPI.repository;

import com.boeani.bloggingAPI.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link Post} entities.
 * <p>
 * Extends {@link JpaRepository} to provide standard CRUD operations,
 * pagination, and sorting support for blog posts.
 * </p>
 */
public interface PostRepository extends JpaRepository<Post, Long> {
}

package com.boeani.bloggingAPI.repository;

import com.boeani.bloggingAPI.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Persistence interface for {@link Post} entities.
 * <p>
 * Inherits CRUD, paging, and sorting operations from {@link JpaRepository}.
 * </p>
 */
public interface PostRepository extends JpaRepository<Post, Long> {
}

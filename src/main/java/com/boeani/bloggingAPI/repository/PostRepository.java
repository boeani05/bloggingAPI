package com.boeani.bloggingAPI.repository;

import com.boeani.bloggingAPI.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Persistence interface for {@link Post} entities.
 * <p>
 * Inherits CRUD, paging, and sorting operations from {@link JpaRepository}.
 * </p>
 */
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findDistinctByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrCategoryContainingIgnoreCase(
            String titleTerm,
            String contentTerm,
            String categoryTerm
    );
}

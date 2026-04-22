package com.boeani.bloggingAPI.service;

import com.boeani.bloggingAPI.dto.request.CreatePostRequest;
import com.boeani.bloggingAPI.entity.Post;
import com.boeani.bloggingAPI.exceptions.*;
import com.boeani.bloggingAPI.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service layer for operations related to {@link Post} entities.
 * <p>
 * Encapsulates business logic and coordinates data access through
 * {@link PostRepository}.
 * </p>
 */
@Service
public class PostService {

    /**
     * Repository used to access and manage post persistence.
     */
    private final PostRepository postRepository;

    /**
     * Creates a new service instance with the required post repository.
     *
     * @param postRepository repository dependency for post data operations
     */
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * Retrieves all available blog posts.
     *
     * @return a list containing all persisted posts
     */
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    /**
     * Retrieves a single post by its unique identifier.
     *
     * @param id the unique identifier of the post to retrieve
     * @return the persisted post matching the provided identifier
     * @throws PostNotFoundException when no post exists for the provided identifier
     */
    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post Not Found"));
    }

    /**
     * Creates a new {@link Post} instance from the incoming request payload.
     * <p>
     * Copies all user-provided fields and initializes creation/update timestamps
     * with the current server time.
     * </p>
     *
     * @param createPostRequest request data used to populate the post
     * @return the newly created and persisted post instance
     */
    public Post createPost(CreatePostRequest createPostRequest) {

        validateCreatePostRequest(createPostRequest);

        Post newPost = new Post();

        LocalDateTime now = LocalDateTime.now();

        newPost.setTitle(createPostRequest.getTitle());
        newPost.setContent(createPostRequest.getContent());
        newPost.setCategory(createPostRequest.getCategory());
        newPost.setTags(createPostRequest.getTags());
        newPost.setCreatedAt(now);
        newPost.setUpdatedAt(now);

        return postRepository.save(newPost);
    }

    /**
     * Updates an existing post identified by its id.
     *
     * @param id identifier of the post to update
     * @param createPostRequest request payload containing updated values
     * @return the persisted post after update
     * @throws PostNotFoundException when no post exists for the given id
     */
    public Post updatePost(Long id, CreatePostRequest createPostRequest) {
        validateCreatePostRequest(createPostRequest);

        Post postToUpdate = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post Not Found"));

        postToUpdate.setTitle(createPostRequest.getTitle());
        postToUpdate.setContent(createPostRequest.getContent());
        postToUpdate.setCategory(createPostRequest.getCategory());
        postToUpdate.setTags(createPostRequest.getTags());
        postToUpdate.setUpdatedAt(LocalDateTime.now());

        return postRepository.save(postToUpdate);
    }

    /**
     * Deletes a post by id.
     *
     * @param id identifier of the post to delete
     * @throws PostNotFoundException when no post exists for the given id
     */
    public void deletePostById(Long id) {
        Post postToDelete = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post Not Found"));

        postRepository.delete(postToDelete);
    }

    /**
     * Validates mandatory request fields for post creation or update.
     *
     * @param createPostRequest request payload to validate
     */
    private void validateCreatePostRequest(CreatePostRequest createPostRequest) {
        if (createPostRequest.getTitle() == null || createPostRequest.getTitle().isEmpty()) {
            throw new TitleNotEnteredException("Title Is Missing");
        }

        if (createPostRequest.getContent() == null || createPostRequest.getContent().isEmpty()) {
            throw new ContentNotEnteredException("Content Is Missing");
        }

        if (createPostRequest.getCategory() == null || createPostRequest.getCategory().isBlank()) {
            throw new CategoryNotEnteredException("Category Is Missing");
        }

        if (createPostRequest.getTags() == null || createPostRequest.getTags().isEmpty()) {
            throw new TagsNotEnteredException("Tags Are Missing");
        }
    }
}

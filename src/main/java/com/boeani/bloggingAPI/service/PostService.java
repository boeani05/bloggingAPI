package com.boeani.bloggingAPI.service;

import com.boeani.bloggingAPI.dto.request.CreatePostRequest;
import com.boeani.bloggingAPI.entity.Post;
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
     * Creates a new {@link Post} instance from the incoming request payload.
     * <p>
     * Copies all user-provided fields and initializes creation/update timestamps
     * with the current server time.
     * </p>
     *
     * @param createPostRequest request data used to populate the post
     * @return the newly created post instance
     */
    public Post createPost(CreatePostRequest createPostRequest) {
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
}

package com.boeani.bloggingAPI.service;

import com.boeani.bloggingAPI.entity.Post;
import com.boeani.bloggingAPI.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @Test
    void shouldReturnEmptyListWhenTermIsNull() {
        List<Post> result = postService.getPostByTerm(null);

        assertEquals(List.of(), result);
        Mockito.verifyNoInteractions(postRepository);
    }

    @Test
    void shouldReturnEmptyListWhenTermIsBlank() {
        List<Post> result = postService.getPostByTerm("   ");

        assertEquals(List.of(), result);
        Mockito.verifyNoInteractions(postRepository);
    }

    @Test
    void shouldTrimTermBeforeSearchingRepository() {
        Post post = new Post();
        post.setId(1L);
        Mockito.when(
                postRepository.findDistinctByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrCategoryContainingIgnoreCase(
                        "test", "test", "test"
                )
        ).thenReturn(List.of(post));

        List<Post> result = postService.getPostByTerm("  test  ");

        assertEquals(1, result.size());
        Mockito.verify(postRepository)
                .findDistinctByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrCategoryContainingIgnoreCase(
                        "test", "test", "test"
                );
    }
}


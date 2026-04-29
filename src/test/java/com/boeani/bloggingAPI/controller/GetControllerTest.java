package com.boeani.bloggingAPI.controller;

import com.boeani.bloggingAPI.entity.Post;
import com.boeani.bloggingAPI.exceptions.GlobalExceptionHandler;
import com.boeani.bloggingAPI.exceptions.PostNotFoundException;
import com.boeani.bloggingAPI.service.PostService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

/**
 * Web-layer tests for {@link GetController} endpoints.
 */
@WebMvcTest(GetController.class)
@Import(GlobalExceptionHandler.class)
class GetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PostService postService;

    /**
     * Verifies that fetching all posts returns HTTP 200.
     */
    @Test
    public void shouldReturn200WhenGettingAllPosts() throws Exception {

        Mockito.when(postService.getAllPosts()).thenReturn(List.of());

        mockMvc.perform(MockMvcRequestBuilders.get("/posts"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"));

        Mockito.verify(postService).getAllPosts();
    }

    /**
     * Verifies that fetching a post by id returns HTTP 200.
     */
    @Test
    void shouldReturn200WhenGettingPostById() throws Exception {
        String title = "Title Test";
        String content = "Content Test";
        String category = "Category Test";
        String tag = "Tag Test";

        Post post = new Post();
        post.setId(1L);
        post.setTitle(title);
        post.setContent(content);
        post.setCategory(category);
        post.setTags(List.of(tag));

        Mockito.when(postService.getPostById(1L)).thenReturn(post);

        mockMvc.perform(MockMvcRequestBuilders.get("/posts/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is(title)));

        Mockito.verify(postService).getPostById(1L);
    }

    /**
     * Verifies HTTP 404 when a post id does not exist.
     */
    @Test
    void shouldReturn404WhenPostIsMissing() throws Exception {
        Mockito.when(postService.getPostById(1L))
                .thenThrow(new PostNotFoundException("Post Not Found"));

        mockMvc.perform(MockMvcRequestBuilders.get("/posts/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        Mockito.verify(postService).getPostById(1L);
    }

    @Test
    void shouldReturn200WhenSearchingPostsByTerm() throws Exception {
        String title = "Title Test";
        String content = "Content Test";
        String category = "Category Test";
        String tag = "Tag Test";

        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setCategory(category);
        post.setTags(List.of(tag));
        Mockito.when(postService.getPostByTerm("test"))
                .thenReturn(List.of(post));

        mockMvc.perform(MockMvcRequestBuilders.get("/posts").param("term", "test"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is(title)));

        Mockito.verify(postService).getPostByTerm("test");
    }

    @Test
    void shouldReturnEmptyListWhenTermIsBlank() throws Exception {
        Mockito.when(postService.getPostByTerm(""))
                .thenReturn(List.of());

        mockMvc.perform(MockMvcRequestBuilders.get("/posts").param("term", ""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"));

        Mockito.verify(postService).getPostByTerm("");
    }
}
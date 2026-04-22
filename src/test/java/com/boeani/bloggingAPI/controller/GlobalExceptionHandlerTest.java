package com.boeani.bloggingAPI.controller;

import com.boeani.bloggingAPI.dto.request.CreatePostRequest;
import com.boeani.bloggingAPI.exceptions.*;
import com.boeani.bloggingAPI.service.PostService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Web-layer tests for {@link GlobalExceptionHandler} mappings.
 */
@WebMvcTest(PostController.class)
@Import(GlobalExceptionHandler.class)
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PostService postService;

    /**
     * Verifies HTTP 400 when title is missing.
     */
    @Test
    void shouldReturn400WhenTitleIsMissing() throws Exception {
        String jsonInput = """
                {
                "content": "Some content",
                "category": "Tech",
                "tags": ["Test"]
                }
                """;

        Mockito.when(postService.createPost(Mockito.any(CreatePostRequest.class)))
                .thenThrow(new TitleNotEnteredException("Title Is Missing"));

        mockMvc
                .perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    /**
     * Verifies HTTP 400 when content is missing.
     */
    @Test
    void shouldReturn400WhenContentIsMissing() throws Exception {
        String jsonInput = """
                {
                "title": "Some Title",
                "category": "Tech",
                "tags": ["Test"]
                }
                """;

        Mockito.when(postService.createPost(Mockito.any(CreatePostRequest.class)))
                .thenThrow(new ContentNotEnteredException("Content Is Missing"));

        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    /**
     * Verifies HTTP 400 when category is missing.
     */
    @Test
    void shouldReturn400WhenCategoryIsMissing() throws Exception {
        String jsonInput = """
                {
                "title": "Some title",
                "content": "Some content",
                "tags": ["Test"]
                }
                """;

        Mockito.when(postService.createPost(Mockito.any(CreatePostRequest.class)))
                .thenThrow(new CategoryNotEnteredException("Category Is Missing"));

        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    /**
     * Verifies HTTP 400 when tags are missing.
     */
    @Test
    void shouldReturn400WhenTagsAreMissing() throws Exception {
        String jsonInput = """
                {
                "title": "Some title",
                "content": "Some content",
                "tags": ["Test"]
                }
                """;

        Mockito.when(postService.createPost(Mockito.any(CreatePostRequest.class)))
                .thenThrow(new TagsNotEnteredException("Tags Are Missing"));

        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
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
    }
}
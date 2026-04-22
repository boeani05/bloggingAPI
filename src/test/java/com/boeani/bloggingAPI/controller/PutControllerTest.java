package com.boeani.bloggingAPI.controller;

import com.boeani.bloggingAPI.dto.request.CreatePostRequest;
import com.boeani.bloggingAPI.entity.Post;
import com.boeani.bloggingAPI.exceptions.*;
import com.boeani.bloggingAPI.service.PostService;
import org.hamcrest.Matchers;
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

import java.time.LocalDateTime;
import java.util.List;

/**
 * Web-layer tests for {@link PutController} update behavior.
 */
@WebMvcTest(PutController.class)
@Import(GlobalExceptionHandler.class)
class PutControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PostService postService;

    /**
     * Verifies successful post updates with HTTP 200 and expected payload fields.
     */
    @Test
    void shouldUpdatePostSuccessfully() throws Exception {
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

        String jsonUpdate = String.format("""
                        {
                        "title": "%s",
                        "content": "%s",
                        "category": "%s",
                        "tags": ["%s"]
                        }
                        """,
                title,
                content,
                category,
                tag
        );

        Mockito.when(postService.updatePost(Mockito.eq(1L), Mockito.any(CreatePostRequest.class)))
                .thenReturn(post);

        mockMvc.perform(MockMvcRequestBuilders.put("/posts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUpdate))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is(title)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is(content)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category", Matchers.is(category)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tags[0]", Matchers.is(tag)));

        Mockito.verify(postService).updatePost(Mockito.eq(1L), Mockito.any(CreatePostRequest.class));
    }

    /**
     * Verifies that a missing post during update returns HTTP 404.
     */
    @Test
    void shouldReturn404WhenUpdatingNonexistentPost() throws Exception {
        String jsonUpdate = """
                {
                "title": "Title Test",
                "content": "Content Test",
                "category": "Category Test",
                "tags": ["Tag Test"]
                }
                """;

        Mockito.when(postService.updatePost(Mockito.eq(1L), Mockito.any(CreatePostRequest.class)))
                .thenThrow(new PostNotFoundException("Post Not Found"));

        mockMvc.perform(MockMvcRequestBuilders.put("/posts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUpdate))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        Mockito.verify(postService).updatePost(Mockito.eq(1L), Mockito.any(CreatePostRequest.class));
    }

    /**
     * Verifies bean validation for missing title during update.
     */
    @Test
    void shouldReturn400WhenUpdatingWithMissingTitle() throws Exception {
        String content = "Content Test";
        String category = "Category Test";
        String tag = "Tag Test";

        String jsonUpdate = String.format("""
                        {
                        "content": "%s",
                        "category": "%s",
                        "tags": ["%s"]
                        }
                        """,
                content,
                category,
                tag
        );

        mockMvc.perform(MockMvcRequestBuilders.put("/posts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUpdate))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verifyNoInteractions(postService);
    }

    /**
     * Verifies bean validation for missing content during update.
     */
    @Test
    void shouldReturn400WhenUpdatingWithMissingContent() throws Exception {
        String title = "Title Test";
        String category = "Category Test";
        String tag = "Tag Test";

        String jsonUpdate = String.format("""
                        {
                        "title": "%s",
                        "category": "%s",
                        "tags": ["%s"]
                        }
                        """,
                title,
                category,
                tag
        );

        mockMvc.perform(MockMvcRequestBuilders.put("/posts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUpdate))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verifyNoInteractions(postService);
    }

    /**
     * Verifies bean validation for missing category during update.
     */
    @Test
    void shouldReturn400WhenUpdatingWithMissingCategory() throws Exception {
        String title = "Title Test";
        String content = "Content Test";
        String tag = "Tag Test";

        String jsonUpdate = String.format("""
                        {
                        "title": "%s",
                        "content": "%s",
                        "tags": ["%s"]
                        }
                        """,
                title,
                content,
                tag
        );

        mockMvc.perform(MockMvcRequestBuilders.put("/posts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUpdate))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verifyNoInteractions(postService);
    }

    /**
     * Verifies bean validation for missing tags during update.
     */
    @Test
    void shouldReturn400WhenUpdatingWithMissingTags() throws Exception {
        String title = "Title Test";
        String content = "Content Test";
        String category = "Category Test";

        String jsonUpdate = String.format("""
                        {
                        "title": "%s",
                        "content": "%s",
                        "category": "%s"
                        }
                        """,
                title,
                content,
                category
        );

        mockMvc.perform(MockMvcRequestBuilders.put("/posts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUpdate))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verifyNoInteractions(postService);
    }

    /**
     * Verifies that all expected fields are present in a successful update response.
     */
    @Test
    void shouldUpdateOnlyProvidedFields() throws Exception {
        String title = "Title Test";
        String content = "Content Test";
        String category = "Category Test";
        String tag = "Tag Test";

        LocalDateTime createdAt = LocalDateTime.of(2024, 1, 1, 10, 0, 0);
        LocalDateTime updatedAt = LocalDateTime.of(2024, 1, 2, 12, 30, 0);

        Post post = new Post();
        post.setId(1L);
        post.setTitle(title);
        post.setContent(content);
        post.setCategory(category);
        post.setTags(List.of(tag));
        post.setCreatedAt(createdAt);
        post.setUpdatedAt(updatedAt);

        String jsonUpdate = String.format("""
                        {
                        "title": "%s",
                        "content": "%s",
                        "category": "%s",
                        "tags": ["%s"]
                        }
                        """,
                title,
                content,
                category,
                tag
        );

        Mockito.when(postService.updatePost(Mockito.eq(1L), Mockito.any(CreatePostRequest.class)))
                .thenReturn(post);

        mockMvc.perform(MockMvcRequestBuilders.put("/posts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUpdate))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is(title)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is(content)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category", Matchers.is(category)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tags[0]", Matchers.is(tag)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt", Matchers.is("2024-01-01T10:00:00")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.updatedAt", Matchers.is("2024-01-02T12:30:00")));

        Mockito.verify(postService).updatePost(Mockito.eq(1L), Mockito.any(CreatePostRequest.class));
    }

    /**
     * Verifies that updatedAt differs from createdAt in the update response.
     */
    @Test
    void shouldUpdateTimestamp() throws Exception {
        String title = "Title Test";
        String content = "Content Test";
        String category = "Category Test";
        String tag = "Tag Test";

        LocalDateTime createdAt = LocalDateTime.of(2024, 1, 1, 10, 0, 0);

        LocalDateTime updatedAt = LocalDateTime.of(2024, 1, 2, 12, 30, 0);

        Post post = new Post();
        post.setId(1L);
        post.setTitle(title);
        post.setContent(content);
        post.setCategory(category);
        post.setTags(List.of(tag));
        post.setCreatedAt(createdAt);
        post.setUpdatedAt(updatedAt);

        String jsonUpdate = String.format("""
                        {
                        "title": "%s",
                        "content": "%s",
                        "category": "%s",
                        "tags": ["%s"]
                        }
                        """,
                title,
                content,
                category,
                tag
        );

        Mockito.when(postService.updatePost(Mockito.eq(1L), Mockito.any(CreatePostRequest.class)))
                .thenReturn(post);

        mockMvc.perform(MockMvcRequestBuilders.put("/posts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUpdate))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt", Matchers.is("2024-01-01T10:00:00")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.updatedAt", Matchers.is("2024-01-02T12:30:00")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.updatedAt").value(Matchers.not("2024-01-01T10:00:00")));

        Mockito.verify(postService).updatePost(Mockito.eq(1L), Mockito.any(CreatePostRequest.class));
    }
}
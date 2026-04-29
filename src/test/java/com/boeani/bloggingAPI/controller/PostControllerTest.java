package com.boeani.bloggingAPI.controller;

import com.boeani.bloggingAPI.dto.request.CreatePostRequest;
import com.boeani.bloggingAPI.entity.Post;
import com.boeani.bloggingAPI.exceptions.GlobalExceptionHandler;
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

import java.util.List;

/**
 * Web-layer tests for {@link PostController} create endpoint behavior.
 */
@WebMvcTest(PostController.class)
@Import(GlobalExceptionHandler.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PostService postService;


    /**
     * Verifies that creating a post returns HTTP 201.
     */
    @Test
    public void shouldReturn201WhenCreatingPost() throws Exception {
        Post post = new Post();

        String jsonInput = """
                {
                "title": "Title Test",
                "content": "Content Test",
                "category": "Category Test",
                "tags": ["Tag Test"]
                }
                """;

        Mockito.when(postService.createPost(Mockito.any(CreatePostRequest.class)))
                .thenReturn(post);

        mockMvc
                .perform(MockMvcRequestBuilders.post("/posts").contentType(MediaType.APPLICATION_JSON).content(jsonInput))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(postService).createPost(Mockito.any(CreatePostRequest.class));
    }

    /**
     * Verifies that the response payload contains the expected post fields.
     */
    @Test
    public void shouldReturnCorrectPostFieldsWhenCreatingPost() throws Exception {
        String title = "Title Test";
        String content = "Content Test";
        String category = "Category Test";
        String tag = "Tag Test";

        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setCategory(category);
        post.setTags(List.of(tag));

        String jsonInput = String.format("""
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

        Mockito.when(postService.createPost(Mockito.any(CreatePostRequest.class))).thenReturn(post);

        mockMvc
                .perform(MockMvcRequestBuilders.post("/posts").contentType(MediaType.APPLICATION_JSON).content(jsonInput))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is(title)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is(content)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category", Matchers.is(category)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tags[0]", Matchers.is(tag)));

        Mockito.verify(postService).createPost(Mockito.any(CreatePostRequest.class));
    }

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

        mockMvc
                .perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verifyNoInteractions(postService);
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

        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verifyNoInteractions(postService);
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

        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verifyNoInteractions(postService);
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
                "category": "Tech"
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verifyNoInteractions(postService);
    }
}
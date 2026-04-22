package com.boeani.bloggingAPI.controller;

import com.boeani.bloggingAPI.dto.request.CreatePostRequest;
import com.boeani.bloggingAPI.entity.Post;
import com.boeani.bloggingAPI.service.PostService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
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
                .andExpect(MockMvcResultMatchers.jsonPath("$.tags", Matchers.is(List.of(tag))));
    }
}
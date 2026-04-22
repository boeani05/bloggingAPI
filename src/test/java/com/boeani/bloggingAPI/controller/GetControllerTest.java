package com.boeani.bloggingAPI.controller;

import com.boeani.bloggingAPI.entity.Post;
import com.boeani.bloggingAPI.service.PostService;
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
 * Web-layer tests for {@link GetController} endpoints.
 */
@WebMvcTest(GetController.class)
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

        mockMvc
                .perform(MockMvcRequestBuilders.get("/posts"))
                .andExpect(MockMvcResultMatchers.status().isOk());
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
        post.setTitle(title);
        post.setContent(content);
        post.setCategory(category);
        post.setTags(List.of(tag));

        String jsonInput = String.format("""
                        {
                        "title": "%s",
                        "content": "%s",
                        "category": "%s",
                        "tags:" ["%s"]
                        }
                        """,
                title,
                content,
                category,
                tag
        );

        Mockito.when(postService.getPostById(1L)).thenReturn(post);

        mockMvc.perform(MockMvcRequestBuilders.get("/posts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
package com.boeani.bloggingAPI.controller;

import com.boeani.bloggingAPI.exceptions.PostNotFoundException;
import com.boeani.bloggingAPI.service.PostService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Web-layer tests for {@link DeleteController} endpoints.
 */
@WebMvcTest(DeleteController.class)
class DeleteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    PostService postService;

    /**
     * Verifies that deleting an existing post returns HTTP 204.
     */
    @Test
    void shouldReturn204WhenDeletingExistingPost() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/posts/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        Mockito.verify(postService).deletePostById(1L);
    }

    /**
     * Verifies that deleting a non-existing post returns HTTP 404.
     */
    @Test
    void shouldReturn404WhenDeletingNonexistentPost() throws Exception {
        Mockito.doThrow(new PostNotFoundException("Post Not Found"))
                .when(postService).deletePostById(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/posts/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        Mockito.verify(postService).deletePostById(1L);
    }

    /**
     * Verifies that a non-numeric id returns HTTP 400 before service invocation.
     */
    @Test
    void shouldReturn400WhenIdIsInvalidFormat() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/posts/abc"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verifyNoInteractions(postService);
    }
}
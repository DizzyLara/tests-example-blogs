package com.dizzy.demoblogstests.controllers;

import com.dizzy.demoblogstests.entities.Blog;
import com.dizzy.demoblogstests.services.BlogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.dizzy.demoblogstests.matchers.ResponseBodyMatchers.responseBody;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BlogController.class)
class BlogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private BlogService blogService;

    @Test
    void postNewBlog_shouldReturn201_whenValidBlog() throws Exception {
        Blog blog = new Blog();
        blog.setTitle("What a beautiful Title");
        blog.setSubtitle("What a beautiful Subtitle");
        Mockito.when(blogService.save(Mockito.any(), Mockito.anyLong())).thenReturn(blog);
        mockMvc.perform(post("/author/1/blogs")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(blog)))
                .andExpect(status().isCreated()).andExpect(responseBody().containsObjectAsJson(blog, Blog.class));

    }

    @Test
    void postNewBlog_shouldReturn404_whenAuthorDoesNotExist() throws Exception {
        Blog blog = new Blog();
        blog.setTitle("What a beautiful Title");
        blog.setSubtitle("What a beautiful Subtitle");
        Mockito.when(blogService.save(Mockito.any(), Mockito.anyLong())).thenThrow(EntityNotFoundException.class);
        mockMvc.perform(post("/author/1/blogs")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(blog)))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllByAuthor() {
    }
}
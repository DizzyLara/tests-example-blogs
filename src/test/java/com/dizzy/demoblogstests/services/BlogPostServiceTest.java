package com.dizzy.demoblogstests.services;

import com.dizzy.demoblogstests.entities.BlogPost;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BlogPostServiceTest {

    @Mock
    BlogService blogService;

    @InjectMocks
    BlogPostService blogPostService;

    @Test
    void findById_deveRetornarNull_quandoBlogNaoEncontrado() {
        // given - dado que nao encontrou entidade
        Mockito.when(blogService.findById(Mockito.any())).thenThrow(EntityNotFoundException.class);
        // when
        List<BlogPost> allPosts = blogPostService.findAllByBlog(1L);
        // then
        assertNotNull(allPosts);
    }
}
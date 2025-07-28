package com.dizzy.demoblogstests.services;


import com.dizzy.demoblogstests.entities.Blog;
import com.dizzy.demoblogstests.entities.BlogPost;
import com.dizzy.demoblogstests.repositories.BlogPostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogPostService {

    final BlogPostRepository blogPostRepository;
    final BlogService blogService;

    public BlogPostService(BlogPostRepository blogPostRepository, BlogService blogService) {
        this.blogPostRepository = blogPostRepository;
        this.blogService = blogService;
    }

    public List<BlogPost> findAllByBlog(Long blogId) {
        try {
            Blog blog = blogService.findById(blogId);
            return blogPostRepository.findAllByBlog(blog);
        } catch (EntityNotFoundException ex) {
            return new ArrayList<>();
        }
    }

    public BlogPost save(BlogPost blogPost, Long blogId) {
        blogPost.setBlog(blogService.findById(blogId));
        return blogPostRepository.save(blogPost);
    }
}

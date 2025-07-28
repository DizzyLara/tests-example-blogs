package com.dizzy.demoblogstests.services;


import com.dizzy.demoblogstests.entities.Blog;
import com.dizzy.demoblogstests.entities.BlogPost;
import com.dizzy.demoblogstests.repositories.BlogPostRepository;
import org.springframework.stereotype.Service;

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
        Blog blog = blogService.findById(blogId);
        if  (blog == null) {
            return null;
        }
        return blogPostRepository.findAllByBlog(blog);
    }

    public BlogPost save(BlogPost blogPost, Long blogId) {
        blogPost.setBlog(blogService.findById(blogId));
        return blogPostRepository.save(blogPost);
    }
}

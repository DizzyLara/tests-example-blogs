package com.dizzy.demoblogstests.controllers;

import com.dizzy.demoblogstests.entities.BlogPost;
import com.dizzy.demoblogstests.services.BlogPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogPostsController {


    private final BlogPostService blogPostService;

    public BlogPostsController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @PostMapping("/blog/{blogId}/posts")
    public ResponseEntity<BlogPost> save(@PathVariable("blogId") Long blogId, @RequestBody BlogPost blogPost) {
        return ResponseEntity.status(HttpStatus.CREATED).body(blogPostService.save(blogPost, blogId));
    }

    @GetMapping("/blog/{blogId}/posts")
    public ResponseEntity<List<BlogPost>> getAllByBlog(@PathVariable("blogId") Long blogId) {
        return ResponseEntity.status(HttpStatus.OK).body(blogPostService.findAllByBlog(blogId));
    }
}

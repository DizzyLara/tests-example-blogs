package com.dizzy.demoblogstests.controllers;


import com.dizzy.demoblogstests.entities.Blog;
import com.dizzy.demoblogstests.exceptions.AuthorNotFoundException;
import com.dizzy.demoblogstests.services.BlogService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/author/{id}/blogs")
    public ResponseEntity<Blog> save(@RequestBody Blog blog, @PathVariable Long id) {
        try {
            Blog createdBlog = blogService.save(blog, id);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBlog);
        } catch (EntityNotFoundException ex) {
            throw new AuthorNotFoundException(ex.getMessage());
        }

    }

    @GetMapping("/author/{id}/blogs")
    public ResponseEntity<List<Blog>> getAllByAuthor(@PathVariable Long id) {

        List<Blog> blogs = blogService.findAllByAuthor(id);
        return ResponseEntity.status(HttpStatus.OK).body(blogs);
    }

    @GetMapping("/blogs")
    public ResponseEntity<List<Blog>> getAllBlogsWithPostsContainingString(@RequestParam Long authorId, @RequestParam String value) {
        List<Blog> blogs = blogService.findAllContaingPostsWithString(value, authorId);
        return blogs.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(blogs) : ResponseEntity.status(HttpStatus.OK).body(blogs);
    }
}

package com.dizzy.demoblogstests.services;

import com.dizzy.demoblogstests.entities.Author;
import com.dizzy.demoblogstests.entities.Blog;
import com.dizzy.demoblogstests.repositories.BlogRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    final BlogRepository blogRepository;
    final AuthorService authorService;

    public BlogService(BlogRepository blogRepository, AuthorService authorService) {
        this.blogRepository = blogRepository;
        this.authorService = authorService;
    }

    public Blog save(Blog blog, Long id) throws EntityNotFoundException {
        blog.setAuthor(authorService.findById(id));
        if (!isValid(blog)) {
            throw new IllegalArgumentException("Blog is invalid");
        }
        return blogRepository.save(blog);
    }

    public Blog findById(Long id) {
        return blogRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Blog with id " + id + " not found"));
    }

    public List<Blog> findAllByAuthor(Long authorId) {
        Author author = authorService.findById(authorId);
        return blogRepository.findAllByAuthor(author);
    }

    public List<Blog> findAllContaingPostsWithString(String value, Long authorId) {
        return blogRepository.findAllBlogsWithPostsContainingString("%" + value + "%", authorId);
    }

    private boolean isValid(Blog blog) {
        boolean titleIsValid = validateTitleLength(blog.getTitle());
        boolean subtitleIsValid = validateSubTitleLength(blog.getSubtitle());
        boolean authorIsNotNull = blog.getAuthor() != null;
        return titleIsValid && subtitleIsValid && authorIsNotNull;
    }

    private boolean validateTitleLength(String title) {
        return title.length() > 8 && title.length() < 65;
    }

    private boolean validateSubTitleLength(String subtitle) {
        return subtitle.length() < 120;
    }
}

package com.dizzy.demoblogstests.controllers;


import com.dizzy.demoblogstests.entities.Author;
import com.dizzy.demoblogstests.services.AuthorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AuthorController {

    final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping(value = "/authors")
    public ResponseEntity<Author> create(@RequestBody Author author) {
        Author createdAuthor = authorService.save(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAuthor);
    }

    @GetMapping(value = "/authors/{id}")
    public ResponseEntity<Author> findById(@PathVariable Long id) {
        try {
            Author author = authorService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(author);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}

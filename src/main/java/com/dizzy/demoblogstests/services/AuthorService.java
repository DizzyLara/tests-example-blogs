package com.dizzy.demoblogstests.services;


import com.dizzy.demoblogstests.entities.Author;
import com.dizzy.demoblogstests.repositories.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author findById(Long id) throws EntityNotFoundException {
        return authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Author with id " + id + " not found"));
    }

    public Author save(Author author) {
        if (!isValid(author.getName())) {
            throw new IllegalArgumentException("Author name is invalid");
        }
        return authorRepository.save(author);
    }

    public List<Author> findAllByName(String name) {
        return authorRepository.findAllByName(name);
    }

    private boolean isValid(String name) {
        return name.matches("([a-zA-Z]+)\\s([a-zA-Z]+)");
    }
}

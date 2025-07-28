package com.dizzy.demoblogstests.repositories;


import com.dizzy.demoblogstests.entities.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {


    List<Author> findAllByName(String name);
}

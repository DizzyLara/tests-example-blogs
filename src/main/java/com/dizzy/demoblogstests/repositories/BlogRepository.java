package com.dizzy.demoblogstests.repositories;

import com.dizzy.demoblogstests.entities.Author;
import com.dizzy.demoblogstests.entities.Blog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BlogRepository extends CrudRepository<Blog, Long> {

    List<Blog> findAllByAuthor(Author author);

    // metodo encontra todos os blogs que contem posts que contem uma string
    @Query("select b from BlogPost p join Blog as b on p.blog.id = b.id where p.content ilike :value AND b.author.id = :authorId GROUP BY(b.id)")
    List<Blog> findAllBlogsWithPostsContainingString(String value, Long authorId);
}

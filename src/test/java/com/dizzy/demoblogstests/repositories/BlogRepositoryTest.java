package com.dizzy.demoblogstests.repositories;

import com.dizzy.demoblogstests.entities.Author;
import com.dizzy.demoblogstests.entities.Blog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
class BlogRepositoryTest {

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private AuthorRepository authorRepository;

    private Blog blog;
    private Author author;

    @BeforeEach
    public void setup() {
        author = new Author();
        author.setName("Big Author");
        authorRepository.save(author);

        blog = new Blog();
        blog.setAuthor(author);
        blog.setTitle("Blog Title");
        blog.setSubtitle("Blog Subtitle");
        blog = blogRepository.save(blog);
    }

    @Test
    @DisplayName("metodo findById encontra e retorna blog corretamente!")
    void givenBlog_whenSaved_thenCanBeFoundById() {
        Optional<Blog> result = blogRepository.findById(blog.getId());
        assertTrue(result.isPresent());
        assertEquals(blog.getTitle(), result.get().getTitle());
        assertEquals(blog.getSubtitle(), result.get().getSubtitle());
    }

    @Test
    @DisplayName("metodo findAllByAuthor retorna lista de blogs quando author e blogs sao validos")
    void givenValidAuthorWithBlogs_whenfindAllByAuthor_thenReturnsListOfBlogs() {
        List<Blog> result = blogRepository.findAllByAuthor(author);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(blog, result.getFirst());
    }
}
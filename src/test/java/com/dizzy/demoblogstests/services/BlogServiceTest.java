package com.dizzy.demoblogstests.services;

import com.dizzy.demoblogstests.entities.Author;
import com.dizzy.demoblogstests.entities.Blog;
import com.dizzy.demoblogstests.repositories.BlogRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class BlogServiceTest {
    @Mock
    private AuthorService authorService;
    @Mock
    private BlogRepository blogRepository;

    @InjectMocks
    private BlogService blogService;

    @Test
    @DisplayName("Quando metodo salvar recebe titulo maior que 65 characteres, lança exception IllegalArgument")
    void metodoSalvar_lancaExcessao_quandoTituloMaiorQue65Characters() {
        // given
        Blog blog = new Blog();
        blog.setTitle("Test com muitos characters tipo assim varios que tal uns 90 characteres tipo varios mesmo");
        blog.setSubtitle("");
        Mockito.when(authorService.findById(Mockito.anyLong())).thenReturn(new Author());

        // when & then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> blogService.save(blog, 1L));

        assertEquals("Blog is invalid", exception.getMessage());
    }

    @Test
    void metodoFindById_lancaExcessao_quandoUsuarioNaoEncontrado() {
        // given
        Long id = 1L;
        Mockito.when(blogRepository.findById(id)).thenReturn(Optional.empty());
        // when & then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> blogService.findById(id));
        // then
        assertEquals("Blog with id " + id + " not found", exception.getMessage());
    }
}
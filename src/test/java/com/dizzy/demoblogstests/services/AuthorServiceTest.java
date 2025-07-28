package com.dizzy.demoblogstests.services;

import com.dizzy.demoblogstests.entities.Author;
import com.dizzy.demoblogstests.repositories.AuthorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    AuthorRepository authorRepository;

    @InjectMocks
    AuthorService authorService;

    @Test
    @DisplayName("metodo salvar deve lançar excessão quando nome do Autor for invalido.")
    void save_deveLancarExcessao_quandoNomeDoAutorForInvalido() {
        // given
        Author author = new Author();
        author.setName("asd2 dasd3");
        // expected
        String message = "Author name is invalid";
        // when & then
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> authorService.save(author));
        // then
        assertEquals(message, ex.getMessage());
    }

    @Test
    void save_deveExecutarRepository_quandoAuthorForValido() {
        // given
        Author author = new Author();
        author.setName("Lara Martins");
        // when
        authorService.save(author);
        // then
        Mockito.verify(authorRepository, Mockito.times(1)).save(author);
    }
}
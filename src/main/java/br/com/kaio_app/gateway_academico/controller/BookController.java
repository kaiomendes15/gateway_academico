package br.com.kaio_app.gateway_academico.controller;

import br.com.kaio_app.gateway_academico.domain.model.ApiError;
import br.com.kaio_app.gateway_academico.domain.model.LivroDTO;
import br.com.kaio_app.gateway_academico.domain.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("livros")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> consultBook(@PathVariable Long id, HttpServletRequest request) {

        Optional<LivroDTO> book = bookService.consultBookData(id);

        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        } else {
            ApiError apiError = new ApiError(
                    HttpStatus.NOT_FOUND.value(),
                    "Dados não foram encontrados.",
                    "Livro com ID" + id + " não foi encontrado.",
                    request.getRequestURI()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
        }
    }

    @GetMapping("")
    public ResponseEntity<Object> consultAllBooks(HttpServletRequest request) {

        Collection<LivroDTO> books = bookService.consultAllSBooks();

        if (books.isEmpty()) {
            ApiError apiError = new ApiError(
                    HttpStatus.NOT_FOUND.value(),
                    "Dados não foram encontrados.",
                    "Nenhum livro foi encontrado no sistema.",
                    request.getRequestURI()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
        } else {
            return ResponseEntity.ok(books);
        }
    }
}

package br.com.kaio_app.gateway_academico.controller;

import br.com.kaio_app.gateway_academico.domain.model.ApiError;
import br.com.kaio_app.gateway_academico.domain.model.LivroDTO;
import br.com.kaio_app.gateway_academico.domain.service.LivroService;
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
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> consultBook(@PathVariable Long id, HttpServletRequest request) {

        Optional<LivroDTO> livro = livroService.consultBookData(id);

        return ResponseEntity.ok(livro.get());
    }

    @GetMapping("")
    public ResponseEntity<Object> consultAllBooks(HttpServletRequest request) {

        Collection<LivroDTO> livros = livroService.consultAllSBooks();
        return ResponseEntity.ok(livros);
    }
}

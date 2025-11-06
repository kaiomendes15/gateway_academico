package br.com.kaio_app.gateway_academico.domain.service;

import br.com.kaio_app.gateway_academico.domain.model.LivroDTO;
import br.com.kaio_app.gateway_academico.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Optional<LivroDTO> consultBookData(Long id) {
        // A lógica agora é simples: apenas busca no repositório em memória.
        // O "DataLoader" já fez o trabalho de buscar na API.
        return bookRepository.findById(id);
    }

    public Collection<LivroDTO> consultAllSBooks() {
        return bookRepository.findAll();
    }
}

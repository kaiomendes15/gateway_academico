package br.com.kaio_app.gateway_academico.domain.service;

import br.com.kaio_app.gateway_academico.domain.model.LivroDTO;
import br.com.kaio_app.gateway_academico.domain.model.exceptions.RecursoNaoEncontradoException;
import br.com.kaio_app.gateway_academico.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    @Autowired
    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Optional<LivroDTO> consultBookData(Long id) {
        Optional<LivroDTO> livro = livroRepository.findById(id);

        if (livro.isEmpty()) {
            throw new RecursoNaoEncontradoException("Livro com ID " + id + " não foi encontrado.");
        }

        return livro;
    }

    public Collection<LivroDTO> consultAllSBooks() {
        Collection<LivroDTO> livros = livroRepository.findAll();

        if (livros.isEmpty()) {
            throw new RecursoNaoEncontradoException("Nenhum livro foi " +
                    "encontrado no sistema.");
        }

        return livros;
    }
}

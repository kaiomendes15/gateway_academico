package br.com.kaio_app.gateway_academico.repository;

import br.com.kaio_app.gateway_academico.domain.model.LivroDTO;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LivroRepository extends InMemoryRepository<LivroDTO> {

    public void alternarStatusLivro(Long livroId) {

        Optional<LivroDTO> livro = this.findById(livroId);

        if (livro.isPresent()) {

            if (livro.get().getStatus().equals("Disponível")) {
                livro.get().setStatus("Indisponível");
            } else {
                livro.get().setStatus("Disponível");
            }
        }
    }
}

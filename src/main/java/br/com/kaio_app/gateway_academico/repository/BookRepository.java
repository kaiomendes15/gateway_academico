package br.com.kaio_app.gateway_academico.repository;

import br.com.kaio_app.gateway_academico.model.LivroDTO;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository extends InMemoryRepository<LivroDTO> {
}

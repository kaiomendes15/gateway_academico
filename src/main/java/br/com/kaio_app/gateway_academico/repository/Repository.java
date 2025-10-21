package br.com.kaio_app.gateway_academico.repository;

import br.com.kaio_app.gateway_academico.model.Identifiable;

import java.util.Collection;
import java.util.Optional;

public interface Repository<T extends Identifiable> {

    void saveAll(Collection<T> items);

    Optional<T> findById(Long id);

    /** Retorna todos os itens em memória. */
    Collection<T> findAll();

    /** Deleta um item pelo ID (útil para a Sprint 3). */
    void deleteById(Long id);
}

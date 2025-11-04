package br.com.kaio_app.gateway_academico.repository;

import br.com.kaio_app.gateway_academico.model.Identifiable;

import java.util.Collection;
import java.util.Optional;

public interface RelacaoRepository<T extends Identifiable> {
    /*
    Interface para representar uma abstração das relações entre:
    ALUNO_DISCIPLINA (MATRICULA)
    ALUNO_LIVRO (RESERVA DE LIVRO)
    */

    // salvar todos os alunos na "tabela de junção em memório"
    void saveAll(Collection<T> items);

    // buscar pelo aluno por id
    Optional<T> findById(Long id);

    /** Retorna todos os itens em memória. */
    Collection<T> findAll();

    /** Deleta um item específico de um certo aluno */
    void deleteById(Long discenteId, Long contentToDeleteId);
}

package br.com.kaio_app.gateway_academico.repository;

import br.com.kaio_app.gateway_academico.domain.model.interfaces.Identifiable;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryRelationRepository<T extends Identifiable> implements RelacaoRepository<T> {

    // Map<Discente.id, Map<Disciplina.id, Disciplina>>
    // Map(ID do registro, Map[ID do aluno, Lista{ID da disciplina}])
    protected final Map<Long, List<Long>> DB_EM_MEMORIA =
            new HashMap<>();
    // minha intenção é guardar as 5 disciplinas matrículadas pelos alunos
    // vou ter um hash com todos os alunos e suas respectivas disciplinas
    // matrículadas

    /*
    Um discente pode simular matrícula em, no máximo, 5 disciplinas simultâneas,
    desde que sua situação acadêmica não esteja trancada e as disciplinas não
    pertençam a outros cursos.

    Não é permitido matricular-se em disciplina sem vagas (informação fornecida
    pelo microsserviço: disciplina).
    */

    @Override
    public void saveAll(Collection<T> items) {
        if (items == null) return;

        // Converte a Coleção<T> (de Discentes) em um Map<Long, Set<Long>>
        Map<Long, ArrayList<Long>> mapaInicializado = items.stream()
                .filter(item -> item != null && item.getId() != null)
                .collect(Collectors.toMap(
                        // a chave é o id do discente
                        Identifiable::getId,
                        // o valor é uma lista, inicialmente vazia.
                        // essa lista vai armazenar os ids das disciplinas
                        item -> new ArrayList<>(),

                        // Em caso de IDs duplicados na lista, o novo substitui o antigo
                        (existente, novo) -> novo
                ));

        // Adiciona todas as chaves de discente inicializadas ao DB de Relação
        DB_EM_MEMORIA.putAll(mapaInicializado);
    }

    @Override
    public List<Long> findById(Long id) {
        return DB_EM_MEMORIA.get(id);
    }

    @Override
    public Map<Long, List<Long>> findAll() {
        return DB_EM_MEMORIA;
    }

    @Override
    public void deleteItemFromListById(Long discenteId, Long contentToDeleteId) {
        DB_EM_MEMORIA.get(discenteId).remove(contentToDeleteId);
    }

    @Override
    public void addItemToList(Long discenteId, Long contentToAddId) {
        DB_EM_MEMORIA.get(discenteId).add(contentToAddId);
    }

    @Override
    public Integer countByDiscenteId(Long discenteId) {
        return DB_EM_MEMORIA.get(discenteId).size();
    }

    @Override
    public boolean discenteContainItem(Long discenteId, Long itemId) {
        return DB_EM_MEMORIA.get(discenteId).contains(itemId);
    }


}

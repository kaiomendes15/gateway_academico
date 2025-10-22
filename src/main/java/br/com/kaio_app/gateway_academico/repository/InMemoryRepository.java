package br.com.kaio_app.gateway_academico.repository;

import br.com.kaio_app.gateway_academico.model.Identifiable;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InMemoryRepository<T extends Identifiable> implements Repository<T> {

    protected final Map<Long, T> DB_EM_MEMORIA = new HashMap<>();

    @Override
    public void saveAll(Collection<T> items) {
        if (items == null) return;

        Map<Long, T> mapa = items.stream()
                
                .filter(item -> item != null && item.getId() != null) // <-- ERRO CORRIGIDO
                .collect(Collectors.toMap(
                        Identifiable::getId,
                        Function.identity(),
                        (existente, novo) -> novo
                ));

        DB_EM_MEMORIA.putAll(mapa);
    }

    // fazer as devidas tratativas!!!!
    @Override
    public Optional<T> findById(Long id) {
        return Optional.ofNullable(DB_EM_MEMORIA.get(id));
    }

    @Override
    public Collection<T> findAll() {
        return DB_EM_MEMORIA.values();
    }

    @Override
    public void deleteById(Long id) {
        DB_EM_MEMORIA.remove(id);
    }
}

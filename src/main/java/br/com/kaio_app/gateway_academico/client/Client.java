package br.com.kaio_app.gateway_academico.client;

import br.com.kaio_app.gateway_academico.model.DiscenteDTO;
import br.com.kaio_app.gateway_academico.model.Identifiable;

import java.util.Map;

public interface Client<T extends Identifiable> {
    public Map<Long, T> getAll();
}

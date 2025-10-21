package br.com.kaio_app.gateway_academico.client;

import br.com.kaio_app.gateway_academico.model.DiscenteDTO;

import java.util.Map;

public interface Client {
    public Map<Long, DiscenteDTO> getAll();
}

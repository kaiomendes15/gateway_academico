package br.com.kaio_app.gateway_academico.repository;

import br.com.kaio_app.gateway_academico.domain.model.DiscenteDTO;
import org.springframework.stereotype.Repository;

@Repository // Anotação do spring
// O Spring vai tratar essa classe como um Data Access Objects (DAOs)
public class DiscenteRepository extends InMemoryRepository<DiscenteDTO>{}

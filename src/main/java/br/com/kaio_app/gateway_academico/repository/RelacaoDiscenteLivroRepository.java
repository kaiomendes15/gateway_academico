package br.com.kaio_app.gateway_academico.repository;

import br.com.kaio_app.gateway_academico.domain.model.DiscenteDTO;
import br.com.kaio_app.gateway_academico.domain.model.LivroDTO;
import org.springframework.stereotype.Repository;

@Repository
public class RelacaoDiscenteLivroRepository extends InMemoryRelationRepository<DiscenteDTO>{
}

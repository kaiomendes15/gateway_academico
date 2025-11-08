package br.com.kaio_app.gateway_academico.repository;

import br.com.kaio_app.gateway_academico.domain.model.DisciplinaDTO;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DisciplinaRepository extends InMemoryRepository<DisciplinaDTO>{

    public void decrementarVagas(Long disciplinaId){
        Optional<DisciplinaDTO> disciplina = this.findById(disciplinaId);

        disciplina.ifPresent(disciplinaDTO -> disciplinaDTO.setVagas(disciplinaDTO.getVagas() - 1));
    }

    public void incrementarVagas(Long disciplinaId){
        Optional<DisciplinaDTO> disciplina = this.findById(disciplinaId);

        disciplina.ifPresent(disciplinaDTO -> disciplinaDTO.setVagas(disciplinaDTO.getVagas() + 1));
    }
}

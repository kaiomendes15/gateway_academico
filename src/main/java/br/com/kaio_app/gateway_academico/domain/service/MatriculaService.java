package br.com.kaio_app.gateway_academico.domain.service;

import br.com.kaio_app.gateway_academico.domain.model.DiscenteDTO;
import br.com.kaio_app.gateway_academico.domain.model.DisciplinaDTO;
import br.com.kaio_app.gateway_academico.repository.DiscenteRepository;
import br.com.kaio_app.gateway_academico.repository.DisciplinaRepository;
import br.com.kaio_app.gateway_academico.repository.RelationDiscenteDisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MatriculaService {

    private final RelationDiscenteDisciplinaRepository matriculaRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final DiscenteRepository discenteRepository;

    @Autowired
    public MatriculaService(
            RelationDiscenteDisciplinaRepository matriculaRepository,
            DisciplinaRepository disciplinaRepository,
            DiscenteRepository discenteRepository
    ) {
        this.matriculaRepository = matriculaRepository;
        this.disciplinaRepository = disciplinaRepository;
        this.discenteRepository = discenteRepository;
    }

    public void matricularAluno(Long discenteId, Long disciplinaId) {
        Optional<DiscenteDTO> discentes =
                discenteRepository.findById(discenteId);
        Optional<DisciplinaDTO> disciplinas =
                disciplinaRepository.findById(disciplinaId);


        if (discentes.isEmpty()) {
            // lancar erro
        }

        if (disciplinas.isEmpty()) {
            // lancar erro
        }

        if (discentes.get().getStatus().equals("Trancado")) {
            // lancar erro
        }

        if (!discentes.get().getCurso().equals(disciplinas.get().getCurso())) {
            // lancar erro
        }

        if (disciplinas.get().getVagas() <= 0) {
            // lancar erro
        }

        if (matriculaRepository.countByDiscenteId(discenteId) >= 5) {
            // lancar erro
        }

        if (matriculaRepository.discenteContainItem(discenteId, disciplinaId)) {
            // lancar erro
        }

        matriculaRepository.addItemToList(discenteId, disciplinaId);

    }
}

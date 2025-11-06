package br.com.kaio_app.gateway_academico.domain.service;

import br.com.kaio_app.gateway_academico.domain.model.DiscenteDTO;
import br.com.kaio_app.gateway_academico.domain.model.DisciplinaDTO;
import br.com.kaio_app.gateway_academico.domain.model.exceptions.*;
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
            throw new RecursoNaoEncontradoException("Discente com ID " + discenteId + " não " +
                    "encontrado.");
        }

        if (disciplinas.isEmpty()) {
            throw new RecursoNaoEncontradoException("Disciplina com ID " + disciplinaId + " não " +
                    "encontrada.");
        }

        if (discentes.get().getStatus().equals("Trancado")) {
            throw new AlunoTrancadoException("O discente " + discentes.get().getNome() + " não pode se matricular pois seu status é 'Trancado'.");
        }

        if (!discentes.get().getCurso().equals(disciplinas.get().getCurso())) {
            throw new CursoIncompativelException("A disciplina '" + disciplinas.get().getNome() + "' não pertence ao curso do discente (" + discentes.get().getCurso() + ").");
        }

        if (disciplinas.get().getVagas() <= 0) {
            throw new DisciplinaSemVagasException("A disciplina '" + disciplinas.get().getNome() + "' não possui vagas disponíveis.");
        }

        if (matriculaRepository.countByDiscenteId(discenteId) >= 5) {
            throw new LimiteMatriculaException("Limite de 5 matrículas simultâneas atingido.");
        }

        if (matriculaRepository.discenteContainItem(discenteId, disciplinaId)) {
            throw new AlunoJaMatriculadoException("O discente já está matriculado nesta disciplina.");
        }

        matriculaRepository.addItemToList(discenteId, disciplinaId);

    }
}

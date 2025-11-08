package br.com.kaio_app.gateway_academico.domain.service;

import br.com.kaio_app.gateway_academico.domain.model.DiscenteDTO;
import br.com.kaio_app.gateway_academico.domain.model.DisciplinaDTO;
import br.com.kaio_app.gateway_academico.domain.model.exceptions.*;
import br.com.kaio_app.gateway_academico.repository.DiscenteRepository;
import br.com.kaio_app.gateway_academico.repository.DisciplinaRepository;
import br.com.kaio_app.gateway_academico.repository.RelationDiscenteDisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Collections.emptyMap;

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

        disciplinaRepository.decrementarVagas(disciplinaId);
        matriculaRepository.addItemToList(discenteId, disciplinaId);

    }

    public Map<Long, DisciplinaDTO> exibirMatriculaAluno(Long discenteId) {
        Optional<DiscenteDTO> discentes =
                discenteRepository.findById(discenteId);

        if (discentes.isEmpty()) {
            throw new RecursoNaoEncontradoException("Discente com ID " + discenteId + " não " +
                    "encontrado.");
        }

        if (discentes.get().getStatus().equals("Trancado")) {
            throw new AlunoTrancadoException("O discente " + discentes.get().getNome() + " está com o curso trancado, logo não possui disciplinas matriculadas.");
        }

        List<Long> disciplinasId =
                matriculaRepository.findById(discenteId);

        Map<Long, DisciplinaDTO> disciplinasMatriculadas = new HashMap<>();

        if (disciplinasId.isEmpty()) {
            return disciplinasMatriculadas;
        }

        for (int i=0; i<disciplinasId.size(); i++) {
            Long disciplinaId = disciplinasId.get(i);
            Optional<DisciplinaDTO> disciplina =
                    disciplinaRepository.findById(disciplinaId);

            if (disciplina.isPresent()) {
                disciplinasMatriculadas.put(disciplinaId, disciplina.get());
            } else {
                throw new RecursoNaoEncontradoException("Disciplina com " +
                        "código " + disciplinaId + " não foi encontrada.");
            }
        }
        return disciplinasMatriculadas;
    }
}

package br.com.kaio_app.gateway_academico.domain.service;

import br.com.kaio_app.gateway_academico.domain.model.DisciplinaDTO;
import br.com.kaio_app.gateway_academico.domain.model.exceptions.RecursoNaoEncontradoException;
import br.com.kaio_app.gateway_academico.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class DisciplinaService {

    // Depende APENAS do repositório
    private final DisciplinaRepository disciplinaRepository;

    @Autowired
    public DisciplinaService(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    public Optional<DisciplinaDTO> consultDisciplineData(Long id) {
        // A lógica agora é simples: apenas busca no repositório em memória.
        // O "DataLoader" já fez o trabalho de buscar na API.
        Optional<DisciplinaDTO> disciplina = disciplinaRepository.findById(id);

        if (disciplina.isEmpty()) {
            throw new RecursoNaoEncontradoException("Disciplina com ID " + id + " não foi encontrada no sistema.");
        }
        return disciplina;
    }

    public Collection<DisciplinaDTO> consultAllDisciplines() {
        Collection<DisciplinaDTO> disciplinas = disciplinaRepository.findAll();

        if (disciplinas.isEmpty()) {
            throw new RecursoNaoEncontradoException("Nenhuma disciplina foi encontrado no sistema.");
        }
        return disciplinas;
    }
}

package br.com.kaio_app.gateway_academico.domain.service;

import br.com.kaio_app.gateway_academico.domain.model.DiscenteDTO;
import br.com.kaio_app.gateway_academico.domain.model.exceptions.RecursoNaoEncontradoException;
import br.com.kaio_app.gateway_academico.repository.DiscenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class DiscenteService {

    // Depende APENAS do repositório
    private final DiscenteRepository discenteRepository;

    @Autowired
    public DiscenteService(DiscenteRepository discenteRepository) {
        this.discenteRepository = discenteRepository;
    }

    public Optional<DiscenteDTO> consultStudentData(Long id) {
        // A lógica agora é simples: apenas busca no repositório em memória.
        // O "DataLoader" já fez o trabalho de buscar na API.
        Optional<DiscenteDTO> discente = discenteRepository.findById(id);

        if (discente.isEmpty()) {
            throw new RecursoNaoEncontradoException("Discente com ID " + id + " não foi encontrado.");
        }
        return discente;
    }

    public Collection<DiscenteDTO> consultAllStudents() {
        Collection<DiscenteDTO> discentes = discenteRepository.findAll();

        if (discentes.isEmpty()) {
            throw new RecursoNaoEncontradoException("Nenhum discente foi " +
                    "encontrado no sistema.");
        }

        return discentes;
    }

    public Collection<DiscenteDTO> buscaDiscentesCurso(String curso) {
        Collection<DiscenteDTO> discentes =
                discenteRepository.buscaDiscentesCurso(curso);

        if (discentes.isEmpty()) {
            throw new RecursoNaoEncontradoException("Nenhum discente com " +
                    "curso " + curso + " foi encontrado no sistema.");
        }

        return discentes;
    }


    public Collection<DiscenteDTO> buscaDiscentesNome(String nome) {
        Collection<DiscenteDTO> discentes =
                discenteRepository.buscaDiscentesNome(nome);

        if (discentes.isEmpty()) {
            throw new RecursoNaoEncontradoException("Nenhum discente com " +
                    "nome " + nome + " foi encontrado no sistema.");
        }

        return discentes;
    }

    public Collection<DiscenteDTO> buscaDiscentesAtivos() {
        Collection<DiscenteDTO> discentes =
                discenteRepository.buscaDiscentesAtivos();

        if (discentes.isEmpty()) {
            throw new RecursoNaoEncontradoException("Nenhum discente com " +
                    "status 'Ativo'" +
                    " foi encontrado no sistema.");
        }

        return discentes;
    }

    public Collection<DiscenteDTO> buscaDiscentesTrancados() {
        Collection<DiscenteDTO> discentes =
                discenteRepository.buscaDiscentesTrancados();

        if (discentes.isEmpty()) {
            throw new RecursoNaoEncontradoException("Nenhum discente com " +
                    "status 'Trancado'" +
                    " foi encontrado no sistema.");
        }

        return discentes;
    }

    public Collection<DiscenteDTO> buscaDiscentesEAD() {
        Collection<DiscenteDTO> discentes =
                discenteRepository.buscaDiscentesEAD();

        if (discentes.isEmpty()) {
            throw new RecursoNaoEncontradoException("Nenhum discente da " +
                    "modalidade 'EAD' foi " +
                    "encontrado no sistema.");
        }

        return discentes;
    }

    public Collection<DiscenteDTO> buscaDiscentesHibrido() {
        Collection<DiscenteDTO> discentes =
                discenteRepository.buscaDiscentesHibrido();

        if (discentes.isEmpty()) {
            throw new RecursoNaoEncontradoException("Nenhum discente da " +
                    "modalidade 'Hibrida' foi " +
                    "encontrado no sistema.");
        }

        return discentes;
    }

    public Collection<DiscenteDTO> buscaDiscentesPresencial() {
        Collection<DiscenteDTO> discentes =
                discenteRepository.buscaDiscentesPresencial();

        if (discentes.isEmpty()) {
            throw new RecursoNaoEncontradoException("Nenhum discente da " +
                    "modalidade 'Presencial' foi " +
                    "encontrado no sistema.");
        }

        return discentes;
    }
}

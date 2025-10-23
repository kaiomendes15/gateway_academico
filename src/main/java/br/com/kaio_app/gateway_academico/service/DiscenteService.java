package br.com.kaio_app.gateway_academico.service;

import br.com.kaio_app.gateway_academico.model.DiscenteDTO;
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
        return discenteRepository.findById(id);
    }

    public Collection<DiscenteDTO> consultAllStudents() {
        return discenteRepository.findAll();
    }
}

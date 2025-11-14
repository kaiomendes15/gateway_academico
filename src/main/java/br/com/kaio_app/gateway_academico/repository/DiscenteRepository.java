package br.com.kaio_app.gateway_academico.repository;

import br.com.kaio_app.gateway_academico.domain.model.DiscenteDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Repository // Anotação do spring
// O Spring vai tratar essa classe como um Data Access Objects (DAOs)
public class DiscenteRepository extends InMemoryRepository<DiscenteDTO>{
    public Collection<DiscenteDTO> buscaDiscentesCurso(String curso) {
        System.out.println("Entrou no metodo");
        Collection<DiscenteDTO> todosDiscentes = this.findAll();
        return todosDiscentes
                .stream()
                .filter(d -> d.getCurso().equalsIgnoreCase(curso))
                .collect(Collectors.toList());
    }

    public Collection<DiscenteDTO> buscaDiscentesNome(String nome) {
        Collection<DiscenteDTO> todosDiscentes = this.findAll();
        return todosDiscentes
                .stream()
                .filter(d -> d.getNome().contains(nome))
                .collect(Collectors.toList());
    }

    public Collection<DiscenteDTO> buscaDiscentesAtivos() {
        Collection<DiscenteDTO> todosDiscentes = this.findAll();
        return todosDiscentes
                .stream()
                .filter(d -> d.getStatus().equalsIgnoreCase("Ativo"))
                .collect(Collectors.toList());
    }

    public Collection<DiscenteDTO> buscaDiscentesTrancados() {
        Collection<DiscenteDTO> todosDiscentes = this.findAll();
        return todosDiscentes
                .stream()
                .filter(d -> !d.getStatus().equalsIgnoreCase("Ativo"))
                .collect(Collectors.toList());
    }

    public Collection<DiscenteDTO> buscaDiscentesEAD() {
        Collection<DiscenteDTO> todosDiscentes = this.findAll();
        return todosDiscentes
                .stream()
                .filter(d -> d.getModalidade().equalsIgnoreCase("EAD"))
                .collect(Collectors.toList());
    }

    public Collection<DiscenteDTO> buscaDiscentesHibrido() {
        Collection<DiscenteDTO> todosDiscentes = this.findAll();
        return todosDiscentes
                .stream()
                .filter(d -> d.getModalidade().equalsIgnoreCase("Híbrido"))
                .collect(Collectors.toList());
    }

    public Collection<DiscenteDTO> buscaDiscentesPresencial() {
        Collection<DiscenteDTO> todosDiscentes = this.findAll();
        return todosDiscentes
                .stream()
                .filter(d -> d.getModalidade().equalsIgnoreCase("Presencial"))
                .collect(Collectors.toList());
    }
}

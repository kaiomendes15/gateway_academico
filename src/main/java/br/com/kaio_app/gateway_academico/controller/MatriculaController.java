package br.com.kaio_app.gateway_academico.controller;

import br.com.kaio_app.gateway_academico.domain.model.DisciplinaDTO;
import br.com.kaio_app.gateway_academico.domain.service.MatriculaService;
import br.com.kaio_app.gateway_academico.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("matricula")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;
    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @PostMapping("/{discenteId}/{disciplinaId}")
    public ResponseEntity<Void> matricular(@PathVariable Long discenteId,
                                           @PathVariable Long disciplinaId) {
        matriculaService.matricularAluno(discenteId, disciplinaId);
        // Se o serviço não lançar exceção, retorna 200 OK (ou 201 Created)
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{discenteId}")
    public ResponseEntity<Map<Long, DisciplinaDTO>> exibirDisciplinasMatriculadas(@PathVariable Long discenteId) {
        Map<Long, DisciplinaDTO> disciplinas =
                matriculaService.exibirMatriculaAluno(discenteId);
        return ResponseEntity.ok().body(disciplinas);
    }

    @PutMapping("/{discenteId}/{disciplinaId}")
    public ResponseEntity<Void> desmatricular(@PathVariable Long discenteId,
                                              @PathVariable Long disciplinaId) {
        matriculaService.desmatricularAluno(discenteId, disciplinaId);

        return ResponseEntity.ok().build();
    }
}

package br.com.kaio_app.gateway_academico.controller;

import br.com.kaio_app.gateway_academico.domain.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("matricula")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @PostMapping("/{discenteId}/{disciplinaId}")
    public ResponseEntity<Void> matricular(@PathVariable Long discenteId,
                                           @PathVariable Long disciplinaId) {
        matriculaService.matricularAluno(discenteId, disciplinaId);
        // Se o serviço não lançar exceção, retorna 200 OK (ou 201 Created)
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{discenteId}")
    public ResponseEntity<List<Long>> exibirDisciplinasMatriculadas(@PathVariable Long discenteId) {
        List<Long> disciplinas =
                matriculaService.exibirMatriculaAluno(discenteId);

        return ResponseEntity.ok().body(disciplinas);
    }
}

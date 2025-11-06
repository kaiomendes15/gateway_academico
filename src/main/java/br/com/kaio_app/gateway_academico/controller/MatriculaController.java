package br.com.kaio_app.gateway_academico.controller;

import br.com.kaio_app.gateway_academico.domain.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

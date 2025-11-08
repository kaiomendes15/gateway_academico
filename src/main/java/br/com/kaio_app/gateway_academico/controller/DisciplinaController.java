package br.com.kaio_app.gateway_academico.controller;

import br.com.kaio_app.gateway_academico.domain.model.ApiError;
import br.com.kaio_app.gateway_academico.domain.model.DisciplinaDTO;
import br.com.kaio_app.gateway_academico.domain.service.DisciplinaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("disciplinas")
public class DisciplinaController {
    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> consultDiscipline(@PathVariable Long id, HttpServletRequest request) {
        // Lembrete -> O optional de um tipo em uma variável é declarar que
        // aquela variável é opcional, ou seja, ela pode receber um valor com
        // aquele tipo especificado ou null.
        Optional<DisciplinaDTO> disciplina =
                disciplinaService.consultDisciplineData(id);
        return ResponseEntity.ok(disciplina.get());
    }

    @GetMapping("")
    public ResponseEntity<Object> consultAllDisciplines(HttpServletRequest request) {

        Collection<DisciplinaDTO> disciplinas =
                disciplinaService.consultAllDisciplines();
        return ResponseEntity.ok(disciplinas);
    }
}

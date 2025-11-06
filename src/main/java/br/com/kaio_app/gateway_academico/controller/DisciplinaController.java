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
        Optional<DisciplinaDTO> discipline =
                disciplinaService.consultDisciplineData(id);

        if (discipline.isPresent()) {
            return ResponseEntity.ok(discipline.get()); // Retorna 200 OK
        } else {
            ApiError errorResponse = new ApiError(
                    HttpStatus.NOT_FOUND.value(),
                    "Dados não foram encontrados.",
                    "Disciplina com ID " + id + " não foi encontrada.",
                    request.getRequestURI()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse); // Retorna 404 Not Found
        }
    }

    @GetMapping("")
    public ResponseEntity<Object> consultAllDisciplines(HttpServletRequest request) {

        Collection<DisciplinaDTO> discipline =
                disciplinaService.consultAllDisciplines();
        if (discipline.isEmpty()) {
            ApiError errorResponse = new ApiError(
                    HttpStatus.NOT_FOUND.value(),
                    "Dados não foram encontrados.",
                    "Nenhuma disciplina foi encontrado no sistema.",
                    request.getRequestURI()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        return ResponseEntity.ok(discipline);
    }
}

package br.com.kaio_app.gateway_academico.controller;

import br.com.kaio_app.gateway_academico.model.ApiError;
import br.com.kaio_app.gateway_academico.model.DiscenteDTO;
import br.com.kaio_app.gateway_academico.service.DiscenteService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/discentes")
public class DiscenteController {

    @Autowired
    private DiscenteService discenteService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> consultStudent(@PathVariable Long id,
                                            HttpServletRequest request) {
        // Lembrete -> O optional de um tipo em uma variável é declarar que
        // aquela variável é opcional, ou seja, ela pode receber um valor com
        // aquele tipo especificado ou null.
        Optional<DiscenteDTO> student =
                discenteService.consultStudentData(id);

        if (student.isPresent()) {
            return ResponseEntity.ok(student.get()); // Retorna 200 OK
        } else {
            ApiError errorResponse = new ApiError(
                    HttpStatus.NOT_FOUND.value(),
                    "Dados não foram encontrados.",
                    "Discente com ID " + id + " não foi encontrado.",
                    request.getRequestURI()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse); // Retorna 404 Not Found
        }
    }

    @GetMapping("")
    public ResponseEntity<Object> consultAllStudents(HttpServletRequest request) {

        Collection<DiscenteDTO> students = discenteService.consultAllStudents();
        if (students.isEmpty()) {
            ApiError errorResponse = new ApiError(
                    HttpStatus.NOT_FOUND.value(),
                    "Dados não foram encontrados.",
                    "Nenhum discente foi encontrado no sistema.",
                    request.getRequestURI()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        return ResponseEntity.ok(students);
    }
}

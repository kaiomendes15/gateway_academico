package br.com.kaio_app.gateway_academico.controller;

import br.com.kaio_app.gateway_academico.domain.model.ApiError;
import br.com.kaio_app.gateway_academico.domain.model.DiscenteDTO;
import br.com.kaio_app.gateway_academico.domain.service.DiscenteService;
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
@RequestMapping("/discentes")
public class DiscenteController {

    @Autowired
    private DiscenteService discenteService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> consultStudent(@PathVariable Long id, HttpServletRequest request) {
        // Lembrete -> O optional de um tipo em uma variável é declarar que
        // aquela variável é opcional, ou seja, ela pode receber um valor com
        // aquele tipo especificado ou null.
        Optional<DiscenteDTO> discente =
                discenteService.consultStudentData(id);

        return ResponseEntity.ok(discente.get());
    }

    @GetMapping("")
    public ResponseEntity<Object> consultAllStudents(HttpServletRequest request) {

        Collection<DiscenteDTO> discentes =
                discenteService.consultAllStudents();
        return ResponseEntity.ok(discentes);
    }
}

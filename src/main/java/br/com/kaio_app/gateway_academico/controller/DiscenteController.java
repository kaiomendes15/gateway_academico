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

    @GetMapping("/curso/{curso}")
    public ResponseEntity<Object> buscaDiscentesCurso(@PathVariable String curso,
                                                      HttpServletRequest request) {
        Collection<DiscenteDTO> discentes =
                discenteService.buscaDiscentesCurso(curso);
        return ResponseEntity.ok(discentes);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Object> buscaDiscentesNome(@PathVariable String nome,
                                                      HttpServletRequest request) {
        Collection<DiscenteDTO> discentes =
                discenteService.buscaDiscentesNome(nome);
        return ResponseEntity.ok(discentes);
    }

    @GetMapping("/status/ativo")
    public ResponseEntity<Object> buscaDiscentesAtivos(HttpServletRequest request) {
        Collection<DiscenteDTO> discentes =
                discenteService.buscaDiscentesAtivos();
        return ResponseEntity.ok(discentes);
    }

    @GetMapping("/status/trancado")
    public ResponseEntity<Object> buscaDiscentesTrancados(HttpServletRequest request) {
        Collection<DiscenteDTO> discentes =
                discenteService.buscaDiscentesTrancados();
        return ResponseEntity.ok(discentes);
    }

    // --- Rotas de Modalidade ---

    @GetMapping("/modalidade/ead")
    public ResponseEntity<Object> buscaDiscentesEAD(HttpServletRequest request) {
        Collection<DiscenteDTO> discentes =
                discenteService.buscaDiscentesEAD();
        return ResponseEntity.ok(discentes);
    }

    @GetMapping("/modalidade/hibrido")
    public ResponseEntity<Object> buscaDiscentesHibrido(HttpServletRequest request) {
        Collection<DiscenteDTO> discentes =
                discenteService.buscaDiscentesHibrido();
        return ResponseEntity.ok(discentes);
    }

    @GetMapping("/modalidade/presencial")
    public ResponseEntity<Object> buscaDiscentesPresencial(HttpServletRequest request) {
        Collection<DiscenteDTO> discentes =
                discenteService.buscaDiscentesPresencial();
        return ResponseEntity.ok(discentes);
    }
}

package br.com.kaio_app.gateway_academico.controller;

import br.com.kaio_app.gateway_academico.domain.model.LivroDTO;
import br.com.kaio_app.gateway_academico.domain.service.ReservaLivroService;
import br.com.kaio_app.gateway_academico.repository.RelacaoDiscenteLivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/reservar")
public class ReservaLivroController {

    @Autowired
    private ReservaLivroService reservaLivroService;


    @PostMapping("/{discenteId}/{livroId}")
    public ResponseEntity<Void> reservar(@PathVariable Long discenteId,
                                         @PathVariable Long livroId) {
        reservaLivroService.reservarLivro(discenteId, livroId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{discenteId}")
    public ResponseEntity<Map<Long, LivroDTO>> exibirLivrosReservados(@PathVariable Long discenteId) {
        Map<Long, LivroDTO> livros = reservaLivroService.exibirLivrosReservados(discenteId);

        return ResponseEntity.ok().body(livros);
    }
}

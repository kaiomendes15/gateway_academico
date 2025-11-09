package br.com.kaio_app.gateway_academico.controller;

import br.com.kaio_app.gateway_academico.domain.service.ReservaLivroService;
import br.com.kaio_app.gateway_academico.repository.RelacaoDiscenteLivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservar")
public class ReservaLivroController {

    @Autowired
    private ReservaLivroService reservaLivroService;

    @Autowired
    private RelacaoDiscenteLivroRepository reservaRepository;

    @PostMapping("/{discenteId}/{livroId}")
    public ResponseEntity<Void> reservar(@PathVariable Long discenteId,
                                         @PathVariable Long livroId) {
        reservaLivroService.reservarLivro(discenteId, livroId);
        return ResponseEntity.ok().build();
    }
}

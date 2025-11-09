package br.com.kaio_app.gateway_academico.domain.service;

import br.com.kaio_app.gateway_academico.domain.model.DiscenteDTO;
import br.com.kaio_app.gateway_academico.domain.model.DisciplinaDTO;
import br.com.kaio_app.gateway_academico.domain.model.LivroDTO;
import br.com.kaio_app.gateway_academico.domain.model.exceptions.*;
import br.com.kaio_app.gateway_academico.repository.DiscenteRepository;
import br.com.kaio_app.gateway_academico.repository.LivroRepository;
import br.com.kaio_app.gateway_academico.repository.RelacaoDiscenteLivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservaLivroService {

    private RelacaoDiscenteLivroRepository reservaLivroRepository;
    private DiscenteRepository discenteRepository;
    private LivroRepository livroRepository;

    @Autowired
    public ReservaLivroService(
            RelacaoDiscenteLivroRepository reservaLivroRepository,
            DiscenteRepository discenteRepository,
            LivroRepository livroRepository
    ) {
        this.livroRepository = livroRepository;
        this.reservaLivroRepository = reservaLivroRepository;
        this.discenteRepository = discenteRepository;
    }

    public void reservarLivro(Long discenteId, Long livroId) {
        Optional<DiscenteDTO> discentes =
                discenteRepository.findById(discenteId);
        Optional<LivroDTO> livro =
                livroRepository.findById(livroId);

        if (discentes.isEmpty()) {
            throw new RecursoNaoEncontradoException("Discente com ID " + discenteId + " não " +
                    "encontrado.");
        }

        if (discentes.get().getStatus().equals("Trancado")) {
            throw new AlunoStatusInvalidoException("O discente " + discentes.get().getNome() + " está com o curso trancado, logo não possui livros reservados.");
        }

        if (discentes.get().getStatus().equals("Cancelado")) {
            throw new AlunoStatusInvalidoException("O discente " + discentes.get().getNome() + " realizou o cancelamento de sua graduação, logo não possui livros reservados.");
        }

        if (livro.get().getStatus().equals("Indisponível")) {
            throw new LivroIndisponivelException("O livro com ID " + livroId +
                    " está indisponível para reserva.");
        }

        if (reservaLivroRepository.countByDiscenteId(discenteId) >= 5) {
            throw new LimiteReservaException("Limite de 5 livros reservados " +
                    "simultâneamente atingido.");
        }

        if (reservaLivroRepository.discenteContainItem(discenteId, livroId)) {
            throw new LivroJaReservadoException("O livro com ID " + livroId + " já foi reservado pelo discente " + discentes.get().getNome());
        }


    }
    public void cancelarReservaLivro(Long discenteId, Long livroId) {}
    public Map<Long, LivroDTO> exibirLivrosReservados(Long discenteId) {
        Optional<DiscenteDTO> discentes =
                discenteRepository.findById(discenteId);

        if (discentes.isEmpty()) {
            throw new RecursoNaoEncontradoException("Discente com ID " + discenteId + " não " +
                    "encontrado.");
        }

        if (discentes.get().getStatus().equals("Trancado")) {
            throw new AlunoStatusInvalidoException("O discente " + discentes.get().getNome() + " está com o curso trancado, logo não possui livros reservados.");
        }

        List<Long> livrosId = reservaLivroRepository.findById(discenteId);
        Map<Long, LivroDTO> livrosReservados = new HashMap<>();

        if (livrosId.isEmpty()) {
            return livrosReservados;
        }

        for (int i=0; i<livrosId.size(); i++) {
            Long livroId = livrosId.get(i);
            Optional<LivroDTO> livro =
                    livroRepository.findById(livroId);

            if (livro.isPresent()) {
                livrosReservados.put(livroId, livro.get());
            } else {
                throw new RecursoNaoEncontradoException("Livro com " +
                        "código " + livroId + " não foi encontrado.");
            }
        }

        return livrosReservados;
    }
}

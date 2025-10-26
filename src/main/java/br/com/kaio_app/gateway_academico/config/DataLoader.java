package br.com.kaio_app.gateway_academico.config;

import br.com.kaio_app.gateway_academico.client.DiscenteClient;
import br.com.kaio_app.gateway_academico.client.DisciplinaClient;
import br.com.kaio_app.gateway_academico.client.LivroClient;
import br.com.kaio_app.gateway_academico.model.DiscenteDTO;
import br.com.kaio_app.gateway_academico.model.DisciplinaDTO;
import br.com.kaio_app.gateway_academico.model.LivroDTO;
import br.com.kaio_app.gateway_academico.repository.BookRepository;
import br.com.kaio_app.gateway_academico.repository.DiscenteRepository;
import br.com.kaio_app.gateway_academico.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import java.util.Map;

@Component
public class DataLoader implements CommandLineRunner {
    // CommandLineRunner => interface do Spring Boot feita exatamente para
    // executar um código na inicialização

    private final DiscenteClient discenteClient;
    private final DiscenteRepository discenteRepository;

    private final DisciplinaClient disciplinaClient;
    private final DisciplinaRepository disciplinaRepository;

    private final LivroClient bookClient;
    private final BookRepository bookRepository;

    @Autowired
    public DataLoader(
            DiscenteClient discenteClient,
            DiscenteRepository discenteRepository,
            DisciplinaClient disciplinaClient,
            DisciplinaRepository disciplinaRepository,
            LivroClient bookClient,
            BookRepository bookRepository
    ) {
        this.disciplinaClient = disciplinaClient;
        this.disciplinaRepository = disciplinaRepository;
        this.discenteClient = discenteClient;
        this.discenteRepository = discenteRepository;
        this.bookClient = bookClient;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            Map<Long, DiscenteDTO> discentes = discenteClient.getAll();
            discenteRepository.saveAll(discentes.values());
            System.out.println("Discentes carregados: " + discentes.size());
        } catch (RestClientException e) {
            System.err.println("Falha ao carregar Discentes: " + e.getMessage());
        }

        try {
            Map<Long, DisciplinaDTO> disciplina = disciplinaClient.getAll();
            disciplinaRepository.saveAll(disciplina.values());
            System.out.println("Disciplinas carregadas: " + disciplina.size());
        } catch (RestClientException e) {
            System.err.println("Falha ao carregar Disciplinas: " + e.getMessage());
        }

        try {
            Map<Long, LivroDTO> books = bookClient.getAll();
            bookRepository.saveAll(books.values());
            System.out.println("Livros carregados: " + books.size());
        } catch (RestClientException e) {
            System.err.println("Falha ao carregar Livros: " + e.getMessage());
        }
    }
}

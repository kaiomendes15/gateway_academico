package br.com.kaio_app.gateway_academico.config;

// 1. IMPORTS ATUALIZADOS
// Removemos DiscenteClient, DisciplinaClient, LivroClient
// Adicionamos a interface genérica Client
import br.com.kaio_app.gateway_academico.client.Client;
import br.com.kaio_app.gateway_academico.model.DiscenteDTO;
import br.com.kaio_app.gateway_academico.model.DisciplinaDTO;
import br.com.kaio_app.gateway_academico.model.LivroDTO;
import br.com.kaio_app.gateway_academico.repository.BookRepository;
import br.com.kaio_app.gateway_academico.repository.DiscenteRepository;
import br.com.kaio_app.gateway_academico.repository.DisciplinaRepository;
import br.com.kaio_app.gateway_academico.repository.RelationDiscenteDisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class DataLoader implements CommandLineRunner {

    // 2. DEPENDÊNCIAS ATUALIZADAS
    // Agora dependemos da interface genérica, não das implementações
    private final Client<DiscenteDTO> discenteClient;
    private final DiscenteRepository discenteRepository;

    private final Client<DisciplinaDTO> disciplinaClient;
    private final DisciplinaRepository disciplinaRepository;

    private final Client<LivroDTO> bookClient;
    private final BookRepository bookRepository;

    private final RelationDiscenteDisciplinaRepository relationDiscenteDisciplinaRepository;

    @Autowired
    // 3. CONSTRUTOR ATUALIZADO
    public DataLoader(
            Client<DiscenteDTO> discenteClient, // <- Mudou
            DiscenteRepository discenteRepository,
            Client<DisciplinaDTO> disciplinaClient, // <- Mudou
            DisciplinaRepository disciplinaRepository,
            Client<LivroDTO> bookClient, // <- Mudou
            BookRepository bookRepository,
            RelationDiscenteDisciplinaRepository relationDiscenteDisciplinaRepository
    ) {
        this.disciplinaClient = disciplinaClient;
        this.disciplinaRepository = disciplinaRepository;
        this.discenteClient = discenteClient;
        this.discenteRepository = discenteRepository;
        this.bookClient = bookClient;
        this.bookRepository = bookRepository;
        this.relationDiscenteDisciplinaRepository = relationDiscenteDisciplinaRepository;
    }

    @Override
    // 4. MÉTODO RUN MUITO MAIS LIMPO
    public void run(String... args) throws Exception {

        // O bloco try-catch (RestClientException) foi removido.
        // A GenericClient já trata a falha e retorna um mapa vazio,
        // cumprindo o requisito de "Degradação Graciosa".

        System.out.println("Iniciando carga de dados dos microsserviços...");

        Map<Long, DiscenteDTO> discentes = discenteClient.getAll();
        discenteRepository.saveAll(discentes.values());
        System.out.println("Discentes carregados: " + discentes.size());
        relationDiscenteDisciplinaRepository.saveAll(discentes.values());
        Map<Long, List<Long>> hash =
                relationDiscenteDisciplinaRepository.findAll();
        for (Map.Entry<Long, List<Long>> entry : hash.entrySet()) {
            Long key = entry.getKey();
            List<Long> value = entry.getValue();
            System.out.println("Usuário " + key + ", Lista de disciplinas " +
                    "matriculadas: " + value);
        }
        System.out.println("Discentes alocados no hashmap de relação.");


        Map<Long, DisciplinaDTO> disciplina = disciplinaClient.getAll();
        disciplinaRepository.saveAll(disciplina.values());
        System.out.println("Disciplinas carregadas: " + disciplina.size());

        Map<Long, LivroDTO> books = bookClient.getAll();
        bookRepository.saveAll(books.values());
        System.out.println("Livros carregados: " + books.size());

        System.out.println("Carga de dados finalizada.");
    }
}
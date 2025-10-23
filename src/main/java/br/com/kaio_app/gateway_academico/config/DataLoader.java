package br.com.kaio_app.gateway_academico.config;

import br.com.kaio_app.gateway_academico.client.DiscenteClient;
import br.com.kaio_app.gateway_academico.model.DiscenteDTO;
import br.com.kaio_app.gateway_academico.repository.DiscenteRepository;
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

    @Autowired
    public DataLoader(DiscenteClient discenteClient, DiscenteRepository discenteRepository) {
        this.discenteClient = discenteClient;
        this.discenteRepository = discenteRepository;
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
    }
}

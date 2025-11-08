package br.com.kaio_app.gateway_academico.config;

import br.com.kaio_app.gateway_academico.client.Client;
import br.com.kaio_app.gateway_academico.client.GenericClient;
import br.com.kaio_app.gateway_academico.domain.model.DiscenteDTO;
import br.com.kaio_app.gateway_academico.domain.model.DisciplinaDTO;
import br.com.kaio_app.gateway_academico.domain.model.LivroDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfig {

    @Bean
    public Client<DiscenteDTO> discenteClient(RestTemplate restTemplate,
                                              @Value("${api.externa.discente.url}") String apiUrl) {

        return new GenericClient<>(
                restTemplate,
                apiUrl,
                DiscenteDTO[].class, // O tipo específico
                "Discentes"          // O nome para o log
        );
    }

    /**
     * Cria o Bean do Client de Disciplinas.
     */
    @Bean
    public Client<DisciplinaDTO> disciplinaClient(RestTemplate restTemplate,
                                                  @Value("${api.externa.disciplina.url}") String apiUrl) {

        return new GenericClient<>(
                restTemplate,
                apiUrl,
                DisciplinaDTO[].class, // O tipo específico
                "Disciplinas"         // O nome para o log
        );
    }

    /**
     * Cria o Bean do Client de Livros.
     */
    @Bean
    public Client<LivroDTO> livroClient(RestTemplate restTemplate,
                                        @Value("${api.externa.biblioteca.url}") String apiUrl) {

        return new GenericClient<>(
                restTemplate,
                apiUrl,
                LivroDTO[].class, // O tipo específico
                "Livros"         // O nome para o log
        );
    }
}

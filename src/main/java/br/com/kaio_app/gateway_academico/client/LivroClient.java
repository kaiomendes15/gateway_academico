package br.com.kaio_app.gateway_academico.client;

import br.com.kaio_app.gateway_academico.model.DiscenteDTO;
import br.com.kaio_app.gateway_academico.model.LivroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class LivroClient implements Client{

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.externa.biblioteca.url}")
    private String libraryApiUrl;

    @Override
    public Map<Long, LivroDTO> getAll() {
        String fullUrl = libraryApiUrl;

        try {
            LivroDTO[] bookArray = restTemplate.getForObject(fullUrl,
                    LivroDTO[].class);

            if (bookArray == null) {
                return Collections.emptyMap();
            }

            return Arrays.stream(bookArray)
                    .filter(discente -> discente != null && discente.getId() != null)
                    .collect(Collectors.toMap(
                            LivroDTO::getId,     // Método de referência para a
                            // Chave (Key)
                            Function.identity()     // Valor (Value) é o próprio objeto discente
                    ));

        } catch (RestClientException e) {
            System.err.println("Erro ao buscar discentes: " + e.getMessage());

            return Collections.emptyMap();
        }
    }
}

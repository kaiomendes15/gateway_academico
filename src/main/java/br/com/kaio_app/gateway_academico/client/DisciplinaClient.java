package br.com.kaio_app.gateway_academico.client;

import br.com.kaio_app.gateway_academico.model.DiscenteDTO;
import br.com.kaio_app.gateway_academico.model.DisciplinaDTO;
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
public class DisciplinaClient implements Client{

    @Autowired
    private RestTemplate restTemplate;

    // Injeta a URL do application.properties
    @Value("${api.externa.disciplina.url}")
    private String disciplinaApiUrl;

    @Override
    public Map<Long, DisciplinaDTO> getAll() {
        String urlCompleta = disciplinaApiUrl;
//        System.out.println(urlCompleta);

        try {
            // 2. Peça um ARRAY de DiscenteDTO, não um único.
            DisciplinaDTO[] disciplinaArray =
                    restTemplate.getForObject(urlCompleta,
                            DisciplinaDTO[].class);

            if (disciplinaArray == null) {
                return Collections.emptyMap();
            }

            return Arrays.stream(disciplinaArray)
                    .filter(disciplina -> disciplina != null && disciplina.getId() != null)
                    .collect(Collectors.toMap(
                            DisciplinaDTO::getId,     // Método de referência para a
                            // Chave (Key)
                            Function.identity()     // Valor (Value) é o próprio objeto discente
                    ));

        } catch (RestClientException e) {
            System.err.println("Erro ao buscar discentes: " + e.getMessage());

            return Collections.emptyMap();
        }
    }
}

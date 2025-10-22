package br.com.kaio_app.gateway_academico.client;

import br.com.kaio_app.gateway_academico.model.DiscenteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component // Marca como um Bean gerenciado pelo Spring
public class DiscenteClient implements Client {

    @Autowired
    private RestTemplate restTemplate;

    // Injeta a URL do application.properties
    @Value("${api.externa.discente.url}")
    private String discenteApiUrl;

    @Override
    public Map<Long, DiscenteDTO> getAll() {
        String urlCompleta = discenteApiUrl;
//        System.out.println(urlCompleta);

        try {
            // 2. Peça um ARRAY de DiscenteDTO, não um único.
            DiscenteDTO[] discentesArray = restTemplate.getForObject(urlCompleta, DiscenteDTO[].class);

            if (discentesArray == null) {
                return Collections.emptyMap();
            }

            return Arrays.stream(discentesArray)
                    .filter(discente -> discente != null && discente.getId() != null)
                    .collect(Collectors.toMap(
                            DiscenteDTO::getId,     // Método de referência para a Chave (Key)
                            Function.identity()     // Valor (Value) é o próprio objeto discente
                    ));

        } catch (Exception e) {
            System.err.println("Erro ao buscar discentes: " + e.getMessage());

            return Collections.emptyMap();
        }
    }


}

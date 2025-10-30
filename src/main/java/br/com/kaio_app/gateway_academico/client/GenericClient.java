package br.com.kaio_app.gateway_academico.client;

import br.com.kaio_app.gateway_academico.model.Identifiable;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GenericClient<T extends Identifiable> implements Client<T> {

    private final RestTemplate restTemplate;
    private final String apiUrl;
    private final Class<T[]> arrayType;
    private final String entityName;

    public GenericClient(RestTemplate restTemplate, String apiUrl,
                         Class<T[]> arrayType, String entityName) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
        this.arrayType = arrayType;
        this.entityName = entityName;
    }

    @Override
    public Map<Long, T> getAll() {
        try {

            T[] entityArray = restTemplate.getForObject(apiUrl, arrayType);

            if (entityArray == null) {
                return Collections.emptyMap();
            }

            return Arrays.stream(entityArray)
                    .filter(entity -> entity != null && entity.getId() != null)
                    .collect(Collectors.toMap(
                            Identifiable::getId,
                            Function.identity()
                    ));

        } catch (RestClientException e) {

            System.err.println("Erro ao buscar " + entityName + ": " + e.getMessage());
            return Collections.emptyMap();
        }
    }
}

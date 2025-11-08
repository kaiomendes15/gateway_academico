package br.com.kaio_app.gateway_academico.domain.model;

import br.com.kaio_app.gateway_academico.domain.model.interfaces.Identifiable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LivroDTO implements Identifiable {
    private Long id;
    private String titulo;
    private String autor;
    private String ano;
    private String status;
}

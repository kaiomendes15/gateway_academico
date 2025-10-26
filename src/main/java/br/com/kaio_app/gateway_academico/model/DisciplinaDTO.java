package br.com.kaio_app.gateway_academico.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DisciplinaDTO implements Identifiable{
    private Long id;
    private String curso;
    private String nome;
    private Integer vagas;
}

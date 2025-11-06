package br.com.kaio_app.gateway_academico.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Gera Getters, Setters, toString, equals, hashCode
@NoArgsConstructor // Gera construtor sem argumentos
// Ignora campos do JSON que não foram mapeados aqui
@JsonIgnoreProperties(ignoreUnknown = true)
public class DiscenteDTO implements Identifiable {

    private Long id;
    private String nome;
    private String curso;
    private String modalidade;
    private String status; // [ativo ou trancado]
}

package br.com.kaio_app.gateway_academico.domain.model.exceptions;

public class LimiteMatriculaException extends RuntimeException {
    public LimiteMatriculaException(String message) {
        super(message);
    }
}

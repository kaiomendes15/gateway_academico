package br.com.kaio_app.gateway_academico.domain.model.exceptions;

public class AlunoStatusInvalidoException extends RuntimeException {
    public AlunoStatusInvalidoException(String message) {
        super(message);
    }
}

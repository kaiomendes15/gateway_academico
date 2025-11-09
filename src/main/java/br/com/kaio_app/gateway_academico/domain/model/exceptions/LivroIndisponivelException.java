package br.com.kaio_app.gateway_academico.domain.model.exceptions;

public class LivroIndisponivelException extends RuntimeException {
    public LivroIndisponivelException(String message) {
        super(message);
    }
}

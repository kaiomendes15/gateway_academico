package br.com.kaio_app.gateway_academico.domain.model.exceptions;

public class CursoIncompativelException extends RuntimeException {
    public CursoIncompativelException(String message) {
        super(message);
    }
}

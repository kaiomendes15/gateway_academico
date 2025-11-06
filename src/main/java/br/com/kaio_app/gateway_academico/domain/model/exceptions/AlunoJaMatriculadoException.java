package br.com.kaio_app.gateway_academico.domain.model.exceptions;

public class AlunoJaMatriculadoException extends RuntimeException {
    public AlunoJaMatriculadoException(String message) {
        super(message);
    }
}

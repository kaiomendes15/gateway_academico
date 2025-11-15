package br.com.kaio_app.gateway_academico.domain.model.exceptions;

public class LivroNaoReservadoPeloDiscenteException extends RuntimeException {
    public LivroNaoReservadoPeloDiscenteException(String message) {
        super(message);
    }
}

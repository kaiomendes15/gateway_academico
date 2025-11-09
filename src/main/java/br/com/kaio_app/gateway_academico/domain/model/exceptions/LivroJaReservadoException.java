package br.com.kaio_app.gateway_academico.domain.model.exceptions;

public class LivroJaReservadoException extends RuntimeException {
  public LivroJaReservadoException(String message) {
    super(message);
  }
}

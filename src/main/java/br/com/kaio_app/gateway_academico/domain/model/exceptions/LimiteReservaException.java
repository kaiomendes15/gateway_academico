package br.com.kaio_app.gateway_academico.domain.model.exceptions;

public class LimiteReservaException extends RuntimeException {
  public LimiteReservaException(String message) {
    super(message);
  }
}

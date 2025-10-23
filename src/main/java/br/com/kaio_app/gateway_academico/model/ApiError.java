package br.com.kaio_app.gateway_academico.model;

import lombok.Data;

import java.time.Instant;

@Data
public class ApiError {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public ApiError(Integer status, String error, String message, String path) {
        this.timestamp = Instant.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}

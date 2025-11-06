package br.com.kaio_app.gateway_academico.controller.exceptions;

import br.com.kaio_app.gateway_academico.domain.model.ApiError;
import br.com.kaio_app.gateway_academico.domain.model.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ApiError> handleRecursoNaoEncontrado(
            RecursoNaoEncontradoException e,
            HttpServletRequest request
    ) {
        ApiError errorResponse = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                "Dados não foram encontrados.",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(AlunoTrancadoException.class)
    public ResponseEntity<ApiError> handleAlunoTrancado(
            RecursoNaoEncontradoException e,
            HttpServletRequest request
    ) {
        ApiError errorResponse = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Aluno com status inválido.",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(CursoIncompativelException.class)
    public ResponseEntity<ApiError> handleCursoIncompativel(
            RecursoNaoEncontradoException e,
            HttpServletRequest request
    ) {
        ApiError errorResponse = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Cursos incompatíveis.",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(DisciplinaSemVagasException.class)
    public ResponseEntity<ApiError> handleDisciplinaSemVagas(
            RecursoNaoEncontradoException e,
            HttpServletRequest request
    ) {
        ApiError errorResponse = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Cursos incompatíveis.",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(LimiteMatriculaException.class)
    public ResponseEntity<ApiError> handleLimiteMatricula(
            RecursoNaoEncontradoException e,
            HttpServletRequest request
    ) {
        ApiError errorResponse = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Cursos incompatíveis.",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(AlunoJaMatriculadoException.class)
    public ResponseEntity<ApiError> handleAlunoJaMatriculado(
            RecursoNaoEncontradoException e,
            HttpServletRequest request
    ) {
        ApiError errorResponse = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Cursos incompatíveis.",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}

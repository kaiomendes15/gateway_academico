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

    @ExceptionHandler(AlunoStatusInvalidoException.class)
    public ResponseEntity<ApiError> handleAlunoTrancado(
            AlunoStatusInvalidoException e,
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
            CursoIncompativelException e,
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
            DisciplinaSemVagasException e,
            HttpServletRequest request
    ) {
        ApiError errorResponse = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Erro ao matricular aluno.",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(LimiteMatriculaException.class)
    public ResponseEntity<ApiError> handleLimiteMatricula(
            LimiteMatriculaException e,
            HttpServletRequest request
    ) {
        ApiError errorResponse = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Erro ao matricular aluno.",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(AlunoJaMatriculadoException.class)
    public ResponseEntity<ApiError> handleAlunoJaMatriculado(
            AlunoJaMatriculadoException e,
            HttpServletRequest request
    ) {
        ApiError errorResponse = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Aluno já matriculado.",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(LivroIndisponivelException.class)
    public ResponseEntity<ApiError> handleRecursoNaoEncontrado(
            LivroIndisponivelException e,
            HttpServletRequest request
    ) {
        ApiError errorResponse = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                "Livro indisponível.",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(LimiteReservaException.class)
    public ResponseEntity<ApiError> handleRecursoNaoEncontrado(
            LimiteReservaException e,
            HttpServletRequest request
    ) {
        ApiError errorResponse = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                "Erro ao reservar o livro.",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(LivroJaReservadoException.class)
    public ResponseEntity<ApiError> handleAlunoJaMatriculado(
            LivroJaReservadoException e,
            HttpServletRequest request
    ) {
        ApiError errorResponse = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Livro já reservado pelo discente.",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(LivroNaoReservadoPeloDiscenteException.class)
    public ResponseEntity<ApiError> handleAlunoJaMatriculado(
            LivroNaoReservadoPeloDiscenteException e,
            HttpServletRequest request
    ) {
        ApiError errorResponse = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Livro não reservado pelo discente.",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}

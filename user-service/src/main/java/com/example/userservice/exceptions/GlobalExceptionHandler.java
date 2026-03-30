package com.example.userservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException(Exception exception){
        return buildProblem(HttpStatus.INTERNAL_SERVER_ERROR, "Internal error",
                "an unexpected error occurred");
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail handleMessageNotReadable(HttpMessageNotReadableException exception){
        return buildProblem(HttpStatus.BAD_REQUEST, "Malformated JSON", "Invalid JSON request");
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationErrors(MethodArgumentNotValidException methodArgumentNotValidException){
        var problem = buildProblem(HttpStatus.BAD_REQUEST, "Validation failed","Invalid request fields");
        Map<String, String> errors = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        problem.setProperty("errors", errors);
        return problem;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail handleUserNotFound(UserNotFoundException userNotFoundException){
        return buildProblem(HttpStatus.NOT_FOUND, "User not found", userNotFoundException.getMessage());
    }

    private ProblemDetail buildProblem(HttpStatus status, String title, String detail){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        problemDetail.setTitle(title);
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }
}

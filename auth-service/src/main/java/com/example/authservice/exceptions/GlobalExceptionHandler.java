package com.example.authservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidTokenException.class)
    public ProblemDetail handleInvalidToken(InvalidTokenException invalidToken){
        return buildProblem("Token invalid", invalidToken.getMessage(), HttpStatus.BAD_REQUEST);
    }
    private ProblemDetail buildProblem(String title, String detail, HttpStatus status){
        var problem = ProblemDetail.forStatusAndDetail(status, detail);
        problem.setTitle(title);
        problem.setProperty("timestamp", LocalDateTime.now());
        return problem;
    }
}

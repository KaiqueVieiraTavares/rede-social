package com.example.userservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

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

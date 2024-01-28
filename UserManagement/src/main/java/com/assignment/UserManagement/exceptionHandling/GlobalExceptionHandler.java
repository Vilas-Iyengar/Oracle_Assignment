package com.assignment.UserManagement.exceptionHandling;

import com.assignment.UserManagement.model.ApiErrors;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.*;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleOtherException(Exception ex){
        String message= ex.getMessage();
        List<String> details= new ArrayList<>();
        details.add("Other exception");
        ApiErrors errors=new ApiErrors(message,details, HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return ResponseEntity.badRequest().body(errors);

    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> userNotFound(Exception ex){
        String message= ex.getMessage();
        List<String> details= new ArrayList<>();
        details.add("User not found exception");
        ApiErrors errors=new ApiErrors(message,details, HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return ResponseEntity.badRequest().body(errors);

    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String message= ex.getMessage();
        List<String> details= new ArrayList<>();
        details.add("path variable is missing");
        ApiErrors errors=new ApiErrors(message,details, HttpStatus.NOT_ACCEPTABLE, LocalDateTime.now());
        return ResponseEntity.status(status).body(errors);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String message= ex.getMessage();
        List<String> details= new ArrayList<>();
        details.add("Mismatch of type");
        ApiErrors errors=new ApiErrors(message,details,HttpStatus.NOT_ACCEPTABLE, LocalDateTime.now());
        return ResponseEntity.status(status).body(errors);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String message= ex.getMessage();
        List<String> details= new ArrayList<>();
        details.add("Method not Supported");
        ApiErrors errors=new ApiErrors(message,details,HttpStatus.METHOD_NOT_ALLOWED, LocalDateTime.now());
        return ResponseEntity.status(status).body(errors);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String message= ex.getMessage();
        List<String> details= new ArrayList<>();
        details.add("Media  not Supported use Json Data");
        ApiErrors errors=new ApiErrors(message,details,HttpStatus.UNSUPPORTED_MEDIA_TYPE, LocalDateTime.now());
        return ResponseEntity.status(status).body(errors);
    }
    //Spring boot 3 new release
    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail runTimeException(RuntimeException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.ALREADY_REPORTED,"Password must have at least 8 characters, one special character, one uppercase, and one lowercase character.");
    }
}

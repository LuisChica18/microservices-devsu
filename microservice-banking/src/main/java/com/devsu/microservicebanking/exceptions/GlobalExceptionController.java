package com.devsu.microservicebanking.exceptions;

import com.devsu.microservicebanking.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
        String mensaje = resourceNotFoundException.getMessage();
        ApiResponse response = new ApiResponse().builder()
                .message(mensaje)
                .success(true)
                .status(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BalanceLessThanZeroException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(BalanceLessThanZeroException resourceNotFoundException){
        String mensaje = resourceNotFoundException.getMessage();
        ApiResponse response = new ApiResponse().builder()
                .message(mensaje)
                .success(true)
                .status(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(AccountNotFoundException resourceNotFoundException){
        String mensaje = resourceNotFoundException.getMessage();
        ApiResponse response = new ApiResponse().builder()
                .message(mensaje)
                .success(true)
                .status(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MovementTypeNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(MovementTypeNotFoundException resourceNotFoundException){
        String mensaje = resourceNotFoundException.getMessage();
        ApiResponse response = new ApiResponse().builder()
                .message(mensaje)
                .success(true)
                .status(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

}

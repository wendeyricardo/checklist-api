package com.learning.springboot.checklistapi.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ExceptionalResponse {

    private LocalDateTime timestamp;
    private String message;
    private HttpStatus status;
    
}

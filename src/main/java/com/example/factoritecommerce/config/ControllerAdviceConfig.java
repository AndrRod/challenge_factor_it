package com.example.factoritecommerce.config;

import com.example.factoritecommerce.excepcion.BadRequestException;
import com.example.factoritecommerce.excepcion.MessageResponse;
import com.example.factoritecommerce.excepcion.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdviceConfig {
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<MessageResponse> notFoundException(NotFoundException exp, HttpServletRequest request){
        return ResponseEntity.status(404).body(new MessageResponse(exp.getMessage(), 404, request.getRequestURI()));
    }
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<MessageResponse> notFoundException(BadRequestException exp, HttpServletRequest request){
        return ResponseEntity.status(400).body(new MessageResponse(exp.getMessage(), 400, request.getRequestURI()));
    }
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Map<String, String>> constraintViolationException(ConstraintViolationException exception, HttpServletRequest request){
        Map<String, String> errors = new HashMap<>();
        exception.getConstraintViolations().stream().forEach((e)->
                errors.put(e.getPropertyPath().toString(), e.getMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    public ResponseEntity<MessageResponse> sQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception, HttpServletRequest request){
        String msgComplete = exception.getMessage();
        String message  = msgComplete.substring(msgComplete.lastIndexOf("entry")+5, msgComplete.lastIndexOf("for"));
        return ResponseEntity.badRequest().body(new MessageResponse("the value"+ message + "its duplicate", HttpStatus.BAD_REQUEST.value(), request.getRequestURL().toString()));
    }
}

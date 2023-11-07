package be.bstorm.exouniversite.pl.controller;

import be.bstorm.exouniversite.bl.models.NotFoundException;
import be.bstorm.exouniversite.pl.models.dtos.Error;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerAdvisor {
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Error> handleNotFoundException(NotFoundException exception, HttpServletRequest req){
        
        Error error = new Error(HttpStatus.NOT_FOUND, exception.getMessage(), LocalDateTime.now(), req.getRequestURI());
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(error);
    }
}

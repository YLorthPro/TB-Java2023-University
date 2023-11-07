package be.bstorm.exouniversite.pl.models.dtos;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record Error (

    HttpStatus status,
    String message,
    LocalDateTime requestMadeAt,
    String URI
    ){

}

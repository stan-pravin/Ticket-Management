package com.example.ticketmanagement.Exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter
@Getter
//@ToString
public class ErrorResponse {
    private final Boolean Success;
    private final HttpStatus status;
    private final String message;

    public ErrorResponse(Boolean success, HttpStatus status, String message, LocalDateTime timestamp) {
        Success = success;
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    private final LocalDateTime timestamp;

}

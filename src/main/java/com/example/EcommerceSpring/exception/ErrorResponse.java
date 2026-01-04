package com.example.EcommerceSpring.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class ErrorResponse {

    private int status;

    private String message;

    private LocalDateTime timeStamp;

    public ErrorResponse(int status,String message,LocalDateTime timeStamp){
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }
}

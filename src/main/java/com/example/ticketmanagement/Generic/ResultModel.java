package com.example.ticketmanagement.Generic;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResultModel <T>{
    private String message;
    private Boolean success;
    private T data;
}

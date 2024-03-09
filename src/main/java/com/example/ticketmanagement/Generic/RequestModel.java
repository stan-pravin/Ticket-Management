package com.example.ticketmanagement.Generic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestModel<T> {
    private T Data;
}

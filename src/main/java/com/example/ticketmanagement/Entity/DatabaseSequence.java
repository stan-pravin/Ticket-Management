package com.example.ticketmanagement.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@Data
@NoArgsConstructor
public class DatabaseSequence {
    @Id
    private String id;
    private int seq;
}

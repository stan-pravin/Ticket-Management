
package com.example.ticketmanagement.Entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.example.ticketmanagement.Entity.Ennum.Category;
import com.example.ticketmanagement.Entity.Ennum.CommentType;
import com.example.ticketmanagement.Entity.Ennum.Priority;
import com.example.ticketmanagement.Entity.Ennum.Status;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ticket")
@Entity
@EnableMongoAuditing
@Configuration
@Data
public class Ticket  {
    @Transient
    public static final String SEQUENCE_NAME="sequence";
    @Id
    public  int id;
    private int userId;
    private int customerId;
    @NonNull
    private String title;
    @NonNull
    private String description;
    private Status status;
    @NonNull
    private Priority priority;
    @NonNull
    private String comment;
    @NonNull
    private CommentType commentType;
    private Date closedDate = new Date(System.currentTimeMillis());
    private Date dueDate = new Date(System.currentTimeMillis());
    @NonNull
    private Category category;
    @CreatedDate
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createDate = LocalDateTime.now();
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @LastModifiedDate
    private LocalDateTime updateDate ;
    @CreatedBy
    private String createdBy ="Stan Pravin";
    @LastModifiedBy
    private String updatedBy = "Stan Pravin";
}

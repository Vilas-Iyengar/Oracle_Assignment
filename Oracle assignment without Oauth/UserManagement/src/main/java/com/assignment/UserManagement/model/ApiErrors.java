package com.assignment.UserManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApiErrors {
    String message;
    List<String> details;
    HttpStatus status;
    LocalDateTime timestamp;
}

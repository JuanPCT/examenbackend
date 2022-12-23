package com.examenfinal.examenbackend.model.responses;

import lombok.Data;

@Data
public class UserResponse {
    
    private boolean login;
    private String username;
    private String email;
    private String message;
    private String token;
}

package com.examenfinal.examenbackend.model.responses;

import lombok.Data;

@Data
public class LoginError {
    private boolean login;
    private String message;
}

package com.example.springsecurity2.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String password;
    private String username;
}

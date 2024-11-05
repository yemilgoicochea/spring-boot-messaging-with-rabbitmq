package com.example.authservice.dto;

import lombok.Getter;

@Getter
public class SaveRequestDto {
    private String name;
    private String surname;
    private String email;
    private String password;
}

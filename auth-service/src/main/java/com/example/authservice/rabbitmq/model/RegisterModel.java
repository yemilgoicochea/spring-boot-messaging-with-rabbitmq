package com.example.authservice.rabbitmq.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class RegisterModel implements Serializable{
    private Long authId;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Date created;
}

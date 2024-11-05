package com.example.userservice.repository.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@Document
public class User {

    @Id
    private String id;
    private Long authId;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;
    private Date created;

}

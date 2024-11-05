package com.example.userservice.repository;

import com.example.userservice.repository.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<User, String> {
}

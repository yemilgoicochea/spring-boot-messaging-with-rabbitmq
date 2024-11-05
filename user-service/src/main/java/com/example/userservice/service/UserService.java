package com.example.userservice.service;

import com.example.userservice.rabbitmq.model.RegisterModel;
import com.example.userservice.repository.IUserRepository;
import com.example.userservice.repository.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(RegisterModel registerModel){
        User user = new User();
        user.setAuthId(registerModel.getAuthId());
        user.setName(registerModel.getName());
        user.setSurname(registerModel.getSurname());
        user.setEmail(registerModel.getEmail());
        user.setPassword(registerModel.getPassword());
        user.setCreated(registerModel.getCreated());

        userRepository.save(user);
        log.info("User saved");
    }
}

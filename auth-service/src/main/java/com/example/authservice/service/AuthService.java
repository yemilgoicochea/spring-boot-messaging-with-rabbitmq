package com.example.authservice.service;

import com.example.authservice.dto.SaveRequestDto;
import com.example.authservice.rabbitmq.model.RegisterModel;
import com.example.authservice.rabbitmq.producer.RegisterProducer;
import com.example.authservice.repository.IAuthRepository;
import com.example.authservice.repository.entity.Auth;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {

    private final ObjectMapper objectMapper;
    private final IAuthRepository authRepository;
    private final RegisterProducer registerProducer;


    public AuthService(ObjectMapper objectMapper, IAuthRepository authRepository, RegisterProducer registerProducer) {
        this.objectMapper = objectMapper;
        this.authRepository = authRepository;
        this.registerProducer = registerProducer;
    }

    public Boolean save(SaveRequestDto dto){
        try {
            Auth auth = new Auth();
            auth.setName(dto.getName());
            auth.setSurname(dto.getSurname());
            auth.setEmail(dto.getEmail());
            auth.setPassword(dto.getPassword());
            authRepository.save(auth);
            log.info("Auth saved");

            RegisterModel registerModel = new RegisterModel();
            registerModel.setAuthId(auth.getId());
            registerModel.setName(auth.getName());
            registerModel.setSurname(auth.getSurname());
            registerModel.setEmail(auth.getEmail());
            registerModel.setPassword(auth.getPassword());
            registerModel.setCreated(auth.getCreated());

            String message = objectMapper.writeValueAsString(registerModel);
            registerProducer.sendRegisterMessage(message);

            return true;
        }catch (Exception e){
            return false;
        }
    }
}

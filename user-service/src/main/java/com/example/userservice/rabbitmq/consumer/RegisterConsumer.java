package com.example.userservice.rabbitmq.consumer;

import com.example.userservice.rabbitmq.model.RegisterModel;
import com.example.userservice.service.UserService;
import com.example.userservice.util.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegisterConsumer {

    private final ObjectMapper objectMapper;

    private final UserService userService;

    @Value("${rabbitmq.queue.auth-to-user-register-queue}")
    private String authRegisterQueue;

    public RegisterConsumer(ObjectMapper objectMapper, UserService userService) {
        this.objectMapper = objectMapper;
        this.userService = userService;
    }

    @RabbitListener(queues = Constants.AUTH_TO_USER_REGISTER_QUEUE)
    public void registerConsumer(String message){
        try {
            String dataReceived = objectMapper.writeValueAsString(message);
            log.info("Message received for the queue: [{}], message: {}", authRegisterQueue, dataReceived);
            RegisterModel registerModel = objectMapper.readValue(message, RegisterModel.class);
            log.info("Message converted to RegisterModel: {}", registerModel);
            userService.save(registerModel);
        }catch (Exception e){
            log.error("Error: {}", e.getMessage(), e);
        }
    }
}

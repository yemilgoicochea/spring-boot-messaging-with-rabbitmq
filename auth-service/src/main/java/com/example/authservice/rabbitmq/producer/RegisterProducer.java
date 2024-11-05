package com.example.authservice.rabbitmq.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegisterProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.auth-exchange}")
    private String authExchange;
        
    @Value("${rabbitmq.routing-key.auth-to-user-register-routing-key}")
    private String authUserRegisterRoutingKey;

    public RegisterProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendRegisterMessage(String message) {
        log.info("Publishing message by the exchange: [{}] and the routing key: [{}], message: {}", authExchange, authUserRegisterRoutingKey, message);
        rabbitTemplate.convertAndSend(authExchange, authUserRegisterRoutingKey, message);
    }
}

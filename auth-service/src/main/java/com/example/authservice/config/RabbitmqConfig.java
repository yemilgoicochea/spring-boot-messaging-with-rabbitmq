package com.example.authservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Value("${rabbitmq.exchange.auth-exchange}")
    private String authExchange;

    @Value("${rabbitmq.queue.auth-to-user-register-queue}")
    private String authRegisterQueue;

    @Value("${rabbitmq.routing-key.auth-to-user-register-routing-key}")
    private String authUserRegisterRoutingKey;

    @Bean
    DirectExchange authExchange(){
        return new DirectExchange(authExchange);
    }

    @Bean
    Queue registerQueue(){
        return new Queue(authRegisterQueue);
    }

    @Bean
    public Binding registerBinding(final DirectExchange authExchange, final Queue registerQueue){
        return BindingBuilder.bind(registerQueue).to(authExchange).with(authUserRegisterRoutingKey);
    }
}

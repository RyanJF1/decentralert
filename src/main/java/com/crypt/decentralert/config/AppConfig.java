package com.crypt.decentralert.config;

import com.crypt.decentralert.mapper.AddressMapper;
import com.crypt.decentralert.mapper.NotificationMapper;
import com.crypt.decentralert.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public AddressMapper addressMapper() {
        return new AddressMapper();
    }
    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }

    @Bean
    public NotificationMapper notificationMapper() {
        return new NotificationMapper();
    }
}

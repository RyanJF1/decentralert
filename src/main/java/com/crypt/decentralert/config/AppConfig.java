package com.crypt.decentralert.config;

import com.crypt.decentralert.mapper.CoinMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public CoinMapper coinMapper() {
        return new CoinMapper();
    }
}

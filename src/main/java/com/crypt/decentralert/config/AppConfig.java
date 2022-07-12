package com.crypt.decentralert.config;

import com.crypt.decentralert.mapper.WalletMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public WalletMapper walletMapper() {
        return new WalletMapper();
    }
}

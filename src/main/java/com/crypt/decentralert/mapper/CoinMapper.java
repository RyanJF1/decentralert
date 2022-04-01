package com.crypt.decentralert.mapper;

import com.crypt.decentralert.entity.Coin;
import com.crypt.decentralert.response.CoinResponse;
import org.springframework.context.annotation.Bean;

import java.util.Set;

public class CoinMapper {

    @Bean
    public CoinMapper coinMapper() {
        return new CoinMapper();
    }

    public CoinResponse toCoinResponse(Coin coin){
        CoinResponse response = new CoinResponse();
        response.setSymbol(coin.getSymbol());
        response.setPrice24h(coin.getPrice24h());
        response.setVolume24h(coin.getVolume24h());
        response.setLastTradePrice(coin.getLastTradePrice());
        return response;
    }

//    public Set<CoinResponse> toCoinSetResponse(Set<Coin> coins){
//
//    }
}

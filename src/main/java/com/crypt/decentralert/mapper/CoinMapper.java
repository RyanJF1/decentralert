package com.crypt.decentralert.mapper;

import com.crypt.decentralert.entity.Coin;
import com.crypt.decentralert.response.CoinResponse;
import org.springframework.context.annotation.Bean;

import java.util.*;
import java.util.stream.Collectors;

public class CoinMapper {

    @Bean
    public CoinMapper coinMapper() {
        return new CoinMapper();
    }

    public Set<CoinResponse> toCoinResponse(Coin[] coins){
        Set<CoinResponse> coinResponses = new HashSet<>();
        CoinResponse response = new CoinResponse();
        coinResponses = Arrays.stream(coins).map(coin -> {
            response.setSymbol(coin.getSymbol());
            response.setLastTradePrice(coin.getLastTradePrice());
            response.setPrice24h(coin.getPrice24h());
            response.setVolume24h(coin.getVolume24h());
            return response;
        }).collect(Collectors.toSet());

        return coinResponses;
    }
    public List<Coin> toCoinEntity(Coin[] coins){
        List<Coin> coinResponses = new ArrayList<>();
        coinResponses = Arrays.stream(coins).map(coin -> {
            Coin response = new Coin();
            response.setSymbol(coin.getSymbol());
            response.setLastTradePrice(coin.getLastTradePrice());
            response.setPrice24h(coin.getPrice24h());
            response.setVolume24h(coin.getVolume24h());
            return response;
        }).collect(Collectors.toList());

        return coinResponses;
    }


//    public Set<CoinResponse> toCoinSetResponse(Set<Coin> coins){
//
//    }
}

package com.crypt.decentralert.service;

import com.crypt.decentralert.entity.Coin;
import com.crypt.decentralert.repository.CoinRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CoinService {

    private final String BLOCKCHAIN_API = "https://api.blockchain.com/v3/exchange";
    private final CoinRepository coinRepository;

    public CoinService(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    public void loadCoins(){
        RestTemplate restTemplate = new RestTemplate();
        String coins = restTemplate.getForObject(BLOCKCHAIN_API + "/tickers", String.class);
    }
}

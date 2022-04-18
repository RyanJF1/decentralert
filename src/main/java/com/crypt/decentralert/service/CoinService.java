package com.crypt.decentralert.service;

import com.crypt.decentralert.entity.Coin;
import com.crypt.decentralert.mapper.CoinMapper;
import com.crypt.decentralert.repository.CoinRepository;
import com.crypt.decentralert.response.CoinResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

import static com.crypt.decentralert.config.Constant.BLOCKCHAIN_API;
import static com.crypt.decentralert.config.Constant.CRYPTOWATCH_API;


@Service
public class CoinService {

@Autowired
    private final CoinRepository coinRepository;
    private final CoinMapper coinMapper;

    public CoinService(CoinRepository coinRepository, CoinMapper coinMapper) {
        this.coinRepository = coinRepository;
        this.coinMapper = coinMapper;
    }
@Transactional
    public void loadCoins(){
        List<Coin> coins = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Coin[]> objects = restTemplate.getForEntity(BLOCKCHAIN_API + "/tickers", Coin[].class);
        Coin[] coinObjects = objects.getBody();
        coins = coinMapper.toCoinEntity(coinObjects);
        coinRepository.saveAllAndFlush(coins);
    }

    public CoinResponse getBtcCoin(){
        CoinResponse response = new CoinResponse();
        RestTemplate restTemplate = new RestTemplate();
        CoinResponse newCoin = restTemplate.getForObject(CRYPTOWATCH_API + "/markets/kraken/btcusd/summary", CoinResponse.class);
        assert newCoin != null;

        return response;
    }

    public Set<CoinResponse> getAllCoins(){
        List<Coin> coins = coinRepository.findAll();
        Set<CoinResponse> coinResponses = new HashSet<>();
        return coins.stream().map(coin -> {
            CoinResponse response = new CoinResponse();
            response.setLastTradePrice(coin.getLastTradePrice());
            response.setSymbol(coin.getSymbol());
            response.setVolume24h(coin.getVolume24h());
            response.setPrice24h(coin.getPrice24h());
            return response;
        }).collect(Collectors.toSet());
    }
}

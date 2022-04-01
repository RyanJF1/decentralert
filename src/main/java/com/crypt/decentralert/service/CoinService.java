package com.crypt.decentralert.service;

import com.crypt.decentralert.entity.Coin;
import com.crypt.decentralert.mapper.CoinMapper;
import com.crypt.decentralert.repository.CoinRepository;
import com.crypt.decentralert.response.CoinResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

import static com.crypt.decentralert.config.Constant.BLOCKCHAIN_API;

@Service
public class CoinService {


    private final CoinRepository coinRepository;
    private final CoinMapper coinMapper;

    public CoinService(CoinRepository coinRepository, CoinMapper coinMapper) {
        this.coinRepository = coinRepository;
        this.coinMapper = coinMapper;
    }

//    public Set<CoinResponse> loadCoins(){
//        RestTemplate restTemplate = new RestTemplate();
//        String coins = restTemplate.getForObject(BLOCKCHAIN_API + "/tickers", String.class);
//    }

    public CoinResponse getCoinBySymbol(String symbol){
        Coin coin = coinRepository.findBySymbol(symbol);
        RestTemplate restTemplate = new RestTemplate();
        Coin newCoin = restTemplate.getForObject(BLOCKCHAIN_API + "/tickers/" + symbol.toUpperCase(), Coin.class);
        assert newCoin != null;
        coin.setLastTradePrice(newCoin.getLastTradePrice());
        coin.setPrice24h(newCoin.getPrice24h());
        coin.setVolume24h(newCoin.getVolume24h());
        coinRepository.save(coin);
        return coinMapper.toCoinResponse(coin);
    }
}

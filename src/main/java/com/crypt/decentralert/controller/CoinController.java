package com.crypt.decentralert.controller;

import com.crypt.decentralert.entity.Coin;
import com.crypt.decentralert.repository.CoinRepository;
import com.crypt.decentralert.response.CoinResponse;
import com.crypt.decentralert.service.CoinService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class CoinController {

    private final CoinRepository coinRepository;
    private final CoinService coinService;

    public CoinController(CoinRepository coinRepository, CoinService coinService){
        this.coinRepository = coinRepository;
        this.coinService = coinService;
    }

    @GetMapping("/coins")
    public Set<CoinResponse> getCoins(){

         Set<CoinResponse> coins = coinService.getAllCoins();
        return coins;
    }


    @PostMapping("/coins")
    public ResponseEntity<?> createCoin(@RequestBody Coin coin) throws URISyntaxException {
        Coin newCoin = coinRepository.save(coin);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/coins/_load")
    public ResponseEntity<?> loadCoins(){
        coinService.loadCoins();
        return ResponseEntity.ok().build();
    }

//    @PutMapping("/{ticker}")
//    public  ResponseEntity updateCoin(@PathVariable String ticker, @RequestBody Coin coin){
//        Coin updateCoin = coinRepository.findBySymbol(ticker);
////        updateCoin.setLastTradePrice(coin.getLastTradePrice());
//        updateCoin = coinRepository.save(updateCoin);
//
//        return ResponseEntity.ok(updateCoin);
//    }

//    @DeleteMapping("/{ticker}")
//    public ResponseEntity deleteCoin(@PathVariable String ticker){
//        coinRepository.deleteBySymbol(ticker);
//        return ResponseEntity.ok().build();
//    }

//    @GetMapping("/{symbol}")
//    public CoinResponse getCoinBySymbol(@PathVariable String symbol){
//        return coinService.getCoinBySymbol(symbol);
//    }

    @RequestMapping(value = "/btcusd", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CoinResponse> getBtcCoin(){

        return ResponseEntity.ok().body(coinService.getBtcCoin());
    }

}

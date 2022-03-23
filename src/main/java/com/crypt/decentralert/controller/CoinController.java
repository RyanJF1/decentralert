package com.crypt.decentralert.controller;

import com.crypt.decentralert.entity.Coin;
import com.crypt.decentralert.repository.CoinRepository;
import com.crypt.decentralert.service.CoinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/coins")
public class CoinController {

    private final CoinRepository coinRepository;
    private final CoinService coinService;

    public CoinController(CoinRepository coinRepository, CoinService coinService){
        this.coinRepository = coinRepository;
        this.coinService = coinService;
    }

    @GetMapping
    public List<Coin> getCoins(){
        return coinRepository.findAll();
    }

    @GetMapping("/{ticker}")
    public Coin getCoin(@PathVariable String ticker){
        return coinRepository.findBySymbol(ticker);
    }

    @PostMapping
    public ResponseEntity createCoin(@RequestBody Coin coin) throws URISyntaxException {
        Coin newCoin = coinRepository.save(coin);
        return ResponseEntity.created(new URI("/coins/" + newCoin.getId())).body(newCoin);
    }

    @PostMapping("/_load")
    public void loadCoins(){
        coinService.loadCoins();
    }

    @PutMapping("/{ticker}")
    public  ResponseEntity updateCoin(@PathVariable String ticker, @RequestBody Coin coin){
        Coin updateCoin = coinRepository.findBySymbol(ticker);
        updateCoin.setLastTradePrice(coin.getLastTradePrice());
        updateCoin = coinRepository.save(updateCoin);

        return ResponseEntity.ok(updateCoin);
    }

    @DeleteMapping("/{ticker}")
    public ResponseEntity deleteCoin(@PathVariable String ticker){
        coinRepository.deleteBySymbol(ticker);
        return ResponseEntity.ok().build();
    }

}

package com.crypt.decentralert.controller;

import com.crypt.decentralert.entity.Wallet;
import com.crypt.decentralert.repository.WalletRepository;
import com.crypt.decentralert.response.WalletResponse;
import com.crypt.decentralert.service.WalletService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class WalletController {

    private final WalletRepository walletRepository;
    private final WalletService walletService;

    public WalletController(WalletRepository walletRepository, WalletService walletService){
        this.walletRepository = walletRepository;
        this.walletService = walletService;
    }

    @GetMapping("/wallets")
    public ResponseEntity<?> getWallets(){
        return ResponseEntity.ok().build();
    }
    @PostMapping("/wallets")
    public ResponseEntity<?> createWallet(@RequestBody Wallet wallet) throws URISyntaxException {
        Wallet newWallet = walletRepository.save(wallet);
        return ResponseEntity.ok().build();
    }

}

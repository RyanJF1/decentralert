package com.crypt.decentralert.mapper;

import com.crypt.decentralert.entity.Wallet;
import com.crypt.decentralert.response.WalletResponse;
import org.springframework.context.annotation.Bean;

import java.util.*;
import java.util.stream.Collectors;

public class WalletMapper {

    @Bean
    public WalletMapper walletMapper() {
        return new WalletMapper();
    }

    public Set<WalletResponse> toWalletResponse(Wallet[] wallets){
        Set<WalletResponse> walletResponses = new HashSet<>();
        WalletResponse response = new WalletResponse();
        walletResponses = Arrays.stream(wallets).map(wallet -> {
            response.setWalletId(wallet.getWalletId());
            response.setNickname(wallet.getNickname());
            return response;
        }).collect(Collectors.toSet());

        return walletResponses;
    }
    public List<Wallet> toWalletEntities(Wallet[] wallets){
        List<Wallet> walletResponses = new ArrayList<>();
        walletResponses = Arrays.stream(wallets).map(wallet -> {
            Wallet response = new Wallet();
            response.setWalletId(wallet.getWalletId());
            response.setNickname(wallet.getNickname());
            return response;
        }).collect(Collectors.toList());

        return walletResponses;
    }


//    public Set<CoinResponse> toCoinSetResponse(Set<Coin> coins){
//
//    }
}

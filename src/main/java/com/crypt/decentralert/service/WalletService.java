package com.crypt.decentralert.service;

import com.crypt.decentralert.entity.Wallet;
import com.crypt.decentralert.mapper.WalletMapper;
import com.crypt.decentralert.repository.WalletRepository;
import com.crypt.decentralert.response.WalletResponse;
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
public class WalletService {

@Autowired
    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;

    public WalletService(WalletRepository walletRepository, WalletMapper walletMapper) {
        this.walletRepository = walletRepository;
        this.walletMapper = walletMapper;
    }

}

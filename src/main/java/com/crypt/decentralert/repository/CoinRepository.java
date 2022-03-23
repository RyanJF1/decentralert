package com.crypt.decentralert.repository;

import com.crypt.decentralert.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepository extends JpaRepository<Coin, Long> {

    Coin findBySymbol(String ticker);

    void deleteBySymbol(String ticker);
}

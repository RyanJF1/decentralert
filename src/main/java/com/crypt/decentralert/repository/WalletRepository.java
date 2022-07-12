package com.crypt.decentralert.repository;

import com.crypt.decentralert.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}

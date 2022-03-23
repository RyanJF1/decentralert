package com.crypt.decentralert.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "coin")
public class Coin implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public float getPrice24h() {
        return price24h;
    }

    public void setPrice24h(float price24h) {
        this.price24h = price24h;
    }

    public float getVolume24h() {
        return volume24h;
    }

    public void setVolume24h(float volume24h) {
        this.volume24h = volume24h;
    }

    public float getLastTradePrice() {
        return lastTradePrice;
    }

    public void setLastTradePrice(float lastTradePrice) {
        this.lastTradePrice = lastTradePrice;
    }

    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("price_24h")
    private float price24h;
    @JsonProperty("volume_24h")
    private float volume24h;
    @JsonProperty("last_trade_price")
    private float lastTradePrice;




}

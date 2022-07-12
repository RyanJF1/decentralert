package com.crypt.decentralert.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class WalletResponse implements Serializable {

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @JsonProperty("wallet_id")
    private String walletId;
    @JsonProperty("nickname")
    private String nickname;
}

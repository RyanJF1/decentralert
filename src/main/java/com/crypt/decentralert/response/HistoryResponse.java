package com.crypt.decentralert.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class HistoryResponse implements Serializable {
    static final long serialVersionUID = 1L;

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getLastSent() {
        return lastSent;
    }

    public void setLastSent(String lastSent) {
        this.lastSent = lastSent;
    }

    private String addressId;
    private String lastSent;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    private String nickname;


}

package com.crypt.decentralert.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class AddressRequest implements Serializable {
    static final long serialVersionUID = 1L;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    @JsonProperty("address_id")
    private String addressId;
    @JsonProperty("nickname")
    private String nickname;
}

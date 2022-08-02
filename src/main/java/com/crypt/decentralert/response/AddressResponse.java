package com.crypt.decentralert.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class AddressResponse implements Serializable {
    static final long serialVersionUID = 1L;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

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

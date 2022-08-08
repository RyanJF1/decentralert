package com.crypt.decentralert.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class NotificationResponse implements Serializable {
    static final long serialVersionUID = 1L;

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public boolean isNotify() {
        return notify;
    }

    public void setNotify(boolean notify) {
        this.notify = notify;
    }

    @JsonProperty("address_id")
    private String addressId;
    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("notify")
    private boolean notify;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}

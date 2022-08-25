package com.crypt.decentralert.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class CreateNotificationRequest implements Serializable {
    static final long serialVersionUID = 1L;

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    @JsonProperty("address_id")
    private String addressId;
}

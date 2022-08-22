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

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getLastSent() {
        return lastSent;
    }

    public void setLastSent(String lastSent) {
        this.lastSent = lastSent;
    }

    private String addressId;
    private String guid;
    private String lastSent;


}

package com.crypt.decentralert.response;

import java.io.Serializable;

public class RawContractResponse implements Serializable {
    static final long serialVersionUID = 1L;

    private String value;
    private String address;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDecimal() {
        return decimal;
    }

    public void setDecimal(String decimal) {
        this.decimal = decimal;
    }

    private String decimal;
}

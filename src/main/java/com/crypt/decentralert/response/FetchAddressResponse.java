package com.crypt.decentralert.response;

import java.io.Serializable;
import java.util.List;

public class FetchAddressResponse implements Serializable {

    static final long serialVersionUID = 1L;

    public String getHash160() {
        return hash160;
    }

    public void setHash160(String hash160) {
        this.hash160 = hash160;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String hash160;
    private String address;

    public String getN_unredeemed() {
        return n_unredeemed;
    }

    public void setN_unredeemed(String n_unredeemed) {
        this.n_unredeemed = n_unredeemed;
    }

    public String getN_tx() {
        return n_tx;
    }

    public void setN_tx(String n_tx) {
        this.n_tx = n_tx;
    }

    public String getTotal_received() {
        return total_received;
    }

    public void setTotal_received(String total_received) {
        this.total_received = total_received;
    }

    public String getTotal_sent() {
        return total_sent;
    }

    public void setTotal_sent(String total_sent) {
        this.total_sent = total_sent;
    }

    public String getFinal_balance() {
        return final_balance;
    }

    public void setFinal_balance(String final_balance) {
        this.final_balance = final_balance;
    }

    private String n_unredeemed;
    private String n_tx;
    private String total_received;
    private String total_sent;
    private String final_balance;


    public List<TxsResponse> getTxs() {
        return txs;
    }

    public void setTxs(List<TxsResponse> txs) {
        this.txs = txs;
    }

    private List<TxsResponse> txs;
}
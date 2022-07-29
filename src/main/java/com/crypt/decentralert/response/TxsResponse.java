package com.crypt.decentralert.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class TxsResponse implements Serializable {
    static final long serialVersionUID = 1L;
    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getVinSz() {
        return vinSz;
    }

    public void setVinSz(String vinSz) {
        this.vinSz = vinSz;
    }

    public String getVoutSz() {
        return voutSz;
    }

    public void setVoutSz(String voutSz) {
        this.voutSz = voutSz;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getReplayedBy() {
        return replayedBy;
    }

    public void setReplayedBy(String replayedBy) {
        this.replayedBy = replayedBy;
    }

    public String getLockTime() {
        return lockTime;
    }

    public void setLockTime(String lockTime) {
        this.lockTime = lockTime;
    }

    public String getDoubleSpend() {
        return doubleSpend;
    }

    public void setDoubleSpend(String doubleSpend) {
        this.doubleSpend = doubleSpend;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBlockIndex() {
        return blockIndex;
    }

    public void setBlockIndex(String blockIndex) {
        this.blockIndex = blockIndex;
    }

    public String getBlockHeight() {
        return blockHeight;
    }

    public void setBlockHeight(String blockHeight) {
        this.blockHeight = blockHeight;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    private String hash;
    private String ver;
    @JsonProperty("vin_sz")
    private String vinSz;
    @JsonProperty("vout_sz")
    private String voutSz;
    private String size;
    private String weight;
    private String fee;
    @JsonProperty("replayed_by")
    private String replayedBy;
    @JsonProperty("lock_time")
    private String lockTime;
    @JsonProperty("double_spend")
    private String doubleSpend;
    private String time;
    @JsonProperty("block_index")
    private String blockIndex;
    @JsonProperty("block_height")
    private String blockHeight;
//    private String inputs;
//    private String outputs;
    private String result;
    private String balance;
}

package com.crypt.decentralert.response;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class TransfersResultResponse implements Serializable {
    static final long serialVersionUID = 1L;
    private String blockNum;
    private String hash;
    private String from;
    private String to;
    private String value;
    private String tokenId;
    private String asset;

    public String getMetadata() {
        return metadata.get("blockTimestamp");
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    private Map<String, String> metadata;

    public String getBlockNum() {
        return blockNum;
    }

    public void setBlockNum(String blockNum) {
        this.blockNum = blockNum;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }


    private RawContractResponse rawContract;

    public RawContractResponse getRawContract() {
        return rawContract;
    }

    public void setRawContract(RawContractResponse rawContract) {
        this.rawContract = rawContract;
    }
}

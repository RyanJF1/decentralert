package com.crypt.decentralert.request;

import java.io.Serializable;
import java.util.List;

public class GetAssetTransfersParam implements Serializable {
    static final long serialVersionUID = 1L;
    private String fromAddress;
    private List<String> contractAddresses;
    private String maxCount;
    private String excludeZeroValue;

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public List<String> getContractAddresses() {
        return contractAddresses;
    }

    public void setContractAddresses(List<String> contractAddresses) {
        this.contractAddresses = contractAddresses;
    }

    public String getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(String maxCount) {
        this.maxCount = maxCount;
    }

    public String getExcludeZeroValue() {
        return excludeZeroValue;
    }

    public void setExcludeZeroValue(String excludeZeroValue) {
        this.excludeZeroValue = excludeZeroValue;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    private List<String> category;

}

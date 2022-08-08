package com.crypt.decentralert.response;

import java.io.Serializable;
import java.util.List;

public class TransfersResponse implements Serializable {
    static final long serialVersionUID = 1L;

    private List<TransfersResultResponse> transfers;
    public List<TransfersResultResponse> getTransfers() {
        return transfers;
    }

    public void setTransfers(List<TransfersResultResponse> transfers) {
        this.transfers = transfers;
    }
}

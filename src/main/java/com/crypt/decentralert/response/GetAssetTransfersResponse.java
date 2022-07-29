package com.crypt.decentralert.response;

import org.springframework.transaction.TransactionSuspensionNotSupportedException;

import java.io.Serializable;
import java.util.List;

public class GetAssetTransfersResponse implements Serializable {
    static final long serialVersionUID = 1L;
     private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }



    private TransfersResponse result;
     private String jsonrpc;

    public TransfersResponse getResult() {
        return result;
    }

    public void setResult(TransfersResponse result) {
        this.result = result;
    }
}

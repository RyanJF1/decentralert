package com.crypt.decentralert.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GetAssetTransfersRequest implements Serializable {

    static final long serialVersionUID = 1L;

    public String getMethod() {
        return method;
    }



    public void setMethod(String method) {
        this.method = method;
    }

    public ArrayList<ParamsRequest> getParams() {
        return params;
    }

    public void setParams(ArrayList<ParamsRequest> params) {
        this.params = params;
    }

    private String jsonrpc;
    private int id;
    @JsonProperty("method")
    private String method;

    private ArrayList<ParamsRequest> params;

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}

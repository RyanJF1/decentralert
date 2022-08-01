package com.crypt.decentralert.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

public class AlchemyApiRequest extends LinkedHashMap<String, Object> implements Serializable {

    static final long serialVersionUID = 1L;

    public AlchemyApiRequest(){
        this.put("jsonrpc", "2.0");
    }

    public void setParams(Object request){
        this.put("params", Collections.singletonList(request));
    }

    public void setMethod(String method){
        this.put("method", method);
    }

    public void setId(int id){
        this.put("id", id);
    }

}

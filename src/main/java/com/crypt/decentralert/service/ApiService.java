package com.crypt.decentralert.service;

import com.crypt.decentralert.config.Constant;
import com.crypt.decentralert.request.AlchemyApiRequest;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    public <T> T callAlchemyApi(AlchemyApiRequest request, Class<T> returnType){
        Gson gson = new Gson();
        Object obj = gson.toJson(request);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject( Constant.ALCHEMY_API_URL, obj, returnType);
    }
}

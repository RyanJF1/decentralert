package com.crypt.decentralert.service;

import com.crypt.decentralert.entity.Address;
import com.crypt.decentralert.mapper.AddressMapper;
import com.crypt.decentralert.repository.AddressRepository;
import com.crypt.decentralert.request.AddressRequest;
import com.crypt.decentralert.request.AlchemyApiRequest;
import com.crypt.decentralert.request.GetAssetTransfersRequest;
import com.crypt.decentralert.request.ParamsRequest;
import com.crypt.decentralert.response.AddressResponse;
import com.crypt.decentralert.response.FetchAddressResponse;
import com.crypt.decentralert.response.GetAssetTransfersResponse;
import com.crypt.decentralert.response.TxsResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import net.minidev.json.JSONObject;
import org.hibernate.cfg.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.management.remote.JMXAddressable;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class AddressService {

@Autowired
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final ObjectMapper objectMapper = new ObjectMapper();
    Logger logger = LoggerFactory.getLogger(AddressService.class);
    private final String BLOCKCHAIN_API_URL = "https://blockchain.info";
    private final String ALCHEMY_API_URL = "https://eth-mainnet.alchemyapi.io/v2/aiqnoMKlsqyjuze2WPf7qRws-wQzanhQ";

    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    public AddressResponse createAddress(AddressRequest addressRequest){
        Address address = addressMapper.toAddressEntity(addressRequest);
        addressRepository.save(address);
        return addressMapper.toAddressResponse(address);
    }

    public List<AddressResponse> getAddresses(){
        List<Address> addresses = addressRepository.findAll();
        return addressMapper.toAddressResponses(addresses);
    }

    public FetchAddressResponse fetchAddress(String address){
        RestTemplate restTemplate = new RestTemplate();
        FetchAddressResponse fetchAddressResponse = restTemplate.getForObject(BLOCKCHAIN_API_URL + "/rawaddr/" + address, FetchAddressResponse.class);
        logger.info("Fetched wallet " + address);
        return fetchAddressResponse;
    }

    public String compareAddresses(String address1, String address2) {
        RestTemplate restTemplate = new RestTemplate();
        FetchAddressResponse fetchAddressResponse1 = restTemplate.getForObject(BLOCKCHAIN_API_URL + "/rawaddr/" + address1, FetchAddressResponse.class);
        logger.info("Fetched wallet " + address1);
        FetchAddressResponse fetchAddressResponse2 = restTemplate.getForObject(BLOCKCHAIN_API_URL + "/rawaddr/" + address2, FetchAddressResponse.class);
        logger.info("Fetched wallet " + address2);

        List<String> hashes1 = fetchAddressResponse1.getTxs().stream().map(TxsResponse::getHash).collect(Collectors.toList());
        List<String> hashes2 = fetchAddressResponse2.getTxs().stream().map(TxsResponse::getHash).collect(Collectors.toList());

        boolean anyMatch = hashes2.stream().anyMatch(hashes1::contains);


        List<String> matchingHashes = new ArrayList<>();
        if (anyMatch) {
            hashes1.forEach(hash1 -> {
                hashes2.forEach(hash2 -> {
                    if (hash1.equals(hash2))
                        matchingHashes.add(hash1);
                });
            });
            return "Match found!" + matchingHashes;
        }
            return "No match found.";
        }

    public void deleteAddress(String addressId){
        Address address = addressRepository.findByAddressId(addressId);
        addressRepository.delete(address);
    }

    public GetAssetTransfersResponse getAssetTransfers(AlchemyApiRequest request){
        AlchemyApiRequest apiRequest = new AlchemyApiRequest();
        ParamsRequest params = new ParamsRequest();
        params.setFromAddress("0x3f5CE5FBFe3E9af3971dD833D26bA9b5C936f0bE");
        params.setContractAddresses(Collections.singletonList("0x7fc66500c84a76ad7e9c93437bfc5ac33e2ddae9"));
        params.setCategory(Collections.singletonList("external"));
        apiRequest.setParams(params);
        apiRequest.setMethod("alchemy_getAssetTransfers");
        GetAssetTransfersResponse getAssetTransfersResponse = callAlchemyApi(request, GetAssetTransfersResponse.class);
        return getAssetTransfersResponse;
    }

    private <T> T callAlchemyApi(AlchemyApiRequest request, Class<T> returnType){
        Gson gson = new Gson();
        Object obj = gson.toJson(request);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject( ALCHEMY_API_URL, obj, returnType);
    }

}

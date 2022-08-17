package com.crypt.decentralert.service;

import com.crypt.decentralert.config.Constant;
import com.crypt.decentralert.entity.Address;
import com.crypt.decentralert.entity.User;
import com.crypt.decentralert.mapper.AddressMapper;
import com.crypt.decentralert.repository.AddressRepository;
import com.crypt.decentralert.repository.UserRepository;
import com.crypt.decentralert.request.AddAddressRequest;
import com.crypt.decentralert.request.AddressRequest;
import com.crypt.decentralert.request.AlchemyApiRequest;
import com.crypt.decentralert.request.ParamsRequest;
import com.crypt.decentralert.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private final AddressRepository addressRepository;

    private final UserRepository userRepository;
    private final AddressMapper addressMapper;
    private final ApiService apiService;

    Logger logger = LoggerFactory.getLogger(AddressService.class);

    public AddressService(AddressRepository addressRepository, UserRepository userRepository, AddressMapper addressMapper, ApiService apiService, JavaMailSender javaMailSender) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.addressMapper = addressMapper;
        this.apiService = apiService;
    }

    public AddressResponse createAddress(AddressRequest addressRequest) {
        Address address = addressMapper.toAddressEntity(addressRequest);
        addressRepository.save(address);
        return addressMapper.toAddressResponse(address);
    }

    public List<AddressResponse> getAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return addressMapper.toAddressResponses(addresses);
    }

    public FetchAddressResponse fetchAddress(String address) {
        RestTemplate restTemplate = new RestTemplate();
        FetchAddressResponse fetchAddressResponse = restTemplate.getForObject(Constant.BLOCKCHAIN_API + "/rawaddr/" + address, FetchAddressResponse.class);
        logger.info("Fetched wallet " + address);
        return fetchAddressResponse;
    }

    public String compareAddresses(String address1, String address2) {
        RestTemplate restTemplate = new RestTemplate();
        AlchemyApiRequest request1 = new AlchemyApiRequest();
        request1.setId(0);
        request1.setMethod("alchemy_getAssetTransfers");
        ParamsRequest params1 = new ParamsRequest();
        params1.put("fromAddress", address1);
        params1.put("category", List.of("external", "internal", "erc20", "erc721", "erc1155"));
        request1.setParams(params1);

        AlchemyApiRequest request2 = new AlchemyApiRequest();
        request2.setId(0);
        request2.setMethod("alchemy_getAssetTransfers");
        ParamsRequest params2 = new ParamsRequest();
        params2.put("fromAddress", address2);
        params2.put("category", List.of("external", "internal", "erc20", "erc721", "erc1155"));
        request2.setParams(params2);
        GetAssetTransfersResponse response1 = apiService.callAlchemyApi(request1, GetAssetTransfersResponse.class);
        GetAssetTransfersResponse response2 = apiService.callAlchemyApi(request2, GetAssetTransfersResponse.class);

        List<String> hashes1 = response1.getResult().getTransfers().stream().map(TransfersResultResponse::getHash).collect(Collectors.toList());
        List<String> hashes2 = response2.getResult().getTransfers().stream().map(TransfersResultResponse::getHash).collect(Collectors.toList());

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

    public void deleteAddress(String addressId) {
        Address address = addressRepository.findByAddressId(addressId);
        addressRepository.delete(address);
    }

    public GetAssetTransfersResponse getAssetTransfers(String address) {
        AlchemyApiRequest request = new AlchemyApiRequest();
        request.setId(0);
        request.setMethod("alchemy_getAssetTransfers");
        ParamsRequest paramsRequest = new ParamsRequest();
        paramsRequest.put("fromAddress", address);
        paramsRequest.put("category", List.of("external", "internal"));
        paramsRequest.put("withMetadata", true);
        request.setParams(paramsRequest);
        GetAssetTransfersResponse response = apiService.callAlchemyApi(request, GetAssetTransfersResponse.class);
        return Objects.requireNonNullElseGet(response, GetAssetTransfersResponse::new);


    }
    public List<GetAssetTransfersUIResponse> getAssetTransfersUI(String address) {
        AlchemyApiRequest request = new AlchemyApiRequest();
        request.setId(0);
        request.setMethod("alchemy_getAssetTransfers");
        ParamsRequest paramsRequest = new ParamsRequest();
        paramsRequest.put("fromAddress", address);
        paramsRequest.put("category", List.of("external", "internal"));
        paramsRequest.put("withMetadata", true);
        request.setParams(paramsRequest);
        GetAssetTransfersResponse response = apiService.callAlchemyApi(request, GetAssetTransfersResponse.class);
        if (null == response)
            return new ArrayList<>();
        return addressMapper.toGetAssetTransfersUIResponse(response);


    }


    public void addAddressToUser(AddAddressRequest request){
        User user = userRepository.findUserByEmail(request.getEmail());
        Address address = addressRepository.findByAddressId(request.getAddressId());
        user.getAddresses().add((address));
        userRepository.save(user);
    }
}

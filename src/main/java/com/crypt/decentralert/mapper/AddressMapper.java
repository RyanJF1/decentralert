package com.crypt.decentralert.mapper;

import com.crypt.decentralert.entity.Address;
import com.crypt.decentralert.request.AddressRequest;
import com.crypt.decentralert.request.GetAssetTransfersRequest;
import com.crypt.decentralert.response.AddressResponse;
import net.minidev.json.JSONObject;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class AddressMapper {

    @Bean
    public AddressMapper addressMapper() {
        return new AddressMapper();
    }

    public AddressResponse toAddressResponse(Address address) {
        AddressResponse response = new AddressResponse();
        response.setId(address.getId());
        response.setAddressId(address.getAddressId());
        response.setNickname(address.getNickname());
        return response;
    }
    public List<AddressResponse> toAddressResponses(List<Address> addresses){
        List<AddressResponse> addressResponses = new ArrayList<>();
        addressResponses = addresses.stream().map(address -> {
            AddressResponse response = new AddressResponse();
            response.setId(address.getId());
            response.setAddressId(address.getAddressId());
            response.setNickname(address.getNickname());
            return response;
        }).collect(Collectors.toList());

        return addressResponses;
    }
    public List<Address> toAddressEntities(Address[] addresses){
        List<Address> addressList = new ArrayList<>();
        addressList = Arrays.stream(addresses).map(address -> {
            Address response = new Address();
            response.setAddressId(address.getAddressId());
            response.setNickname(address.getNickname());
            return response;
        }).collect(Collectors.toList());

        return addressList;
    }


    public Address toAddressEntity(AddressRequest addressRequest){
        Address address = new Address();
        address.setAddressId(addressRequest.getAddressId());
        address.setNickname(addressRequest.getNickname());
        return address;
    }

    public LinkedHashMap<String, Object> toGetAssetTransfersRequest(Object request){
        Optional<Field> field = Arrays.stream(request.getClass().getDeclaredFields()).findFirst();
        return new LinkedHashMap<>();
    }
}

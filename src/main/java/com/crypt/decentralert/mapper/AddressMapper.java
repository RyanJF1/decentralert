package com.crypt.decentralert.mapper;

import com.crypt.decentralert.entity.Address;
import com.crypt.decentralert.request.AddressRequest;
import com.crypt.decentralert.request.GetAssetTransfersRequest;
import com.crypt.decentralert.request.ParamsRequest;
import com.crypt.decentralert.response.AddressResponse;
import com.crypt.decentralert.response.GetAssetTransfersResponse;
import com.crypt.decentralert.response.GetAssetTransfersUIResponse;
import com.crypt.decentralert.response.TransfersResultResponse;
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
        response.setGuid(address.getGuid());
        response.setAddressId(address.getAddressId());
        response.setNickname(address.getNickname());
        return response;
    }
    public List<AddressResponse> toAddressResponses(List<Address> addresses){
        List<AddressResponse> addressResponses = new ArrayList<>();
        addressResponses = addresses.stream().map(address -> {
            AddressResponse response = new AddressResponse();
            response.setGuid(address.getGuid());
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
        address.setGuid(UUID.randomUUID().toString());
        return address;
    }

    public List<GetAssetTransfersUIResponse> toGetAssetTransfersUIResponse(GetAssetTransfersResponse response){
        List<GetAssetTransfersUIResponse> transfersResultResponses = new ArrayList<>();
        transfersResultResponses = response.getResult().getTransfers().stream().map(transfer -> {
            GetAssetTransfersUIResponse result = new GetAssetTransfersUIResponse();
            result.setAsset(transfer.getAsset());
            result.setBlockNum(transfer.getBlockNum());
            result.setHash(transfer.getHash());
            result.setFrom(transfer.getFrom());
            result.setTo(transfer.getTo());
            result.setValue(transfer.getValue());
            result.setTime(transfer.getMetadata());
            return result;
        }).collect(Collectors.toList());
        Collections.reverse(transfersResultResponses);
        return transfersResultResponses;
    }

}

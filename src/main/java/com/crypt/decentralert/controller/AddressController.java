package com.crypt.decentralert.controller;

import com.crypt.decentralert.request.AddressRequest;
import com.crypt.decentralert.request.GetAssetTransfersRequest;
import com.crypt.decentralert.response.AddressResponse;
import com.crypt.decentralert.response.FetchAddressResponse;
import com.crypt.decentralert.response.GetAssetTransfersResponse;
import com.crypt.decentralert.service.AddressService;
import com.fasterxml.jackson.core.JsonProcessingException;
import net.minidev.json.JSONObject;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService){
        this.addressService = addressService;
    }

    @GetMapping("/address")
    public ResponseEntity<?> getAddresses(){
        List<AddressResponse> response = addressService.getAddresses();
        return ResponseEntity.ok().body(response);
    }
    @PostMapping(value = "/address")
    public ResponseEntity<?> createAddress(@RequestBody AddressRequest addressRequest) {
        AddressResponse response = addressService.createAddress(addressRequest);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("address/{address_id}")
    public ResponseEntity<?> fetchAddress(@PathVariable("address_id") String address){
        FetchAddressResponse response = addressService.fetchAddress(address);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/address/_compare")
    public ResponseEntity<?> compareAddresses(@Param("address1") String address1,
                                              @Param("address2") String address2){
        String response = addressService.compareAddresses(address1, address2);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("address/{address_id}")
    public ResponseEntity<?> deleteAddress(@PathVariable("address_id") String address){
        addressService.deleteAddress(address);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/address/_getAssetTransfers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAssetTransfers(@RequestBody LinkedHashMap<String, Object> request) throws NoSuchFieldException {
        GetAssetTransfersResponse response = addressService.getAssetTransfers(request);
        return ResponseEntity.ok().body(response);
    }
}

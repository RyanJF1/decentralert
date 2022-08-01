package com.crypt.decentralert.controller;

import com.crypt.decentralert.request.AddressRequest;
import com.crypt.decentralert.request.AlchemyApiRequest;
import com.crypt.decentralert.response.AddressResponse;
import com.crypt.decentralert.response.FetchAddressResponse;
import com.crypt.decentralert.service.AddressService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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



    @GetMapping(value = "/address/{address}/_getAssetTransfers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAssetTransfers(@PathVariable("address") String address) {
        Object response = addressService.getAssetTransfers(address);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "/address/_getTokenMetadata", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTokenMetadata(@RequestBody AlchemyApiRequest request) {
        Object response = addressService.getTokenMetadata(request);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "/address/_getTokenBalances", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTokenBalances(@RequestBody AlchemyApiRequest request) {
        Object response = addressService.getTokenBalances(request);
        return ResponseEntity.ok().body(response);
    }
}

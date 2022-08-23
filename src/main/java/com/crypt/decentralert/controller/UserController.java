package com.crypt.decentralert.controller;

import com.crypt.decentralert.request.AddressRequest;
import com.crypt.decentralert.request.AlchemyApiRequest;
import com.crypt.decentralert.request.LoginRequest;
import com.crypt.decentralert.request.UserRequest;
import com.crypt.decentralert.response.AddressResponse;
import com.crypt.decentralert.response.FetchAddressResponse;
import com.crypt.decentralert.response.UserResponse;
import com.crypt.decentralert.service.AddressService;
import com.crypt.decentralert.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUsers(){
        List<UserResponse> response = userService.getUsers();
        return ResponseEntity.ok().body(response);
    }
    @PostMapping(value = "/user")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {
        UserResponse response = userService.createUser(userRequest);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/user/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable("email") String email) {
        userService.deleteUser(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/user")
    public ResponseEntity<?> updateUser(@RequestBody UserRequest userRequest) {
        UserResponse response = userService.updateUser(userRequest);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){
        String response = userService.loginUser(loginRequest);
        return ResponseEntity.ok().body(response);
    }
}

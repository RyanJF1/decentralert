package com.crypt.decentralert.mapper;

import com.crypt.decentralert.entity.Address;
import com.crypt.decentralert.entity.User;
import com.crypt.decentralert.repository.UserRepository;
import com.crypt.decentralert.request.AddressRequest;
import com.crypt.decentralert.request.UserRequest;
import com.crypt.decentralert.response.AddressResponse;
import com.crypt.decentralert.response.UserResponse;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserMapper {

    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }

    public User toUserEntity(UserRequest userRequest){
        User user = new User();
        user.setGuid(UUID.randomUUID().toString());
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        return user;
    }

    public UserResponse toUserResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        return userResponse;
    }

    public List<UserResponse> toUserResponses(List<User> users){
        return users.stream().map(user -> {
            UserResponse response = new UserResponse();
            response.setName(user.getName());
            response.setEmail(user.getEmail());
            return response;
        }).collect(Collectors.toList());
    }
}

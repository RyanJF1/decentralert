package com.crypt.decentralert.service;

import com.crypt.decentralert.entity.User;
import com.crypt.decentralert.mapper.UserMapper;
import com.crypt.decentralert.repository.UserRepository;
import com.crypt.decentralert.request.LoginRequest;
import com.crypt.decentralert.request.UserRequest;
import com.crypt.decentralert.response.UserResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder encoder;
    private UserMapper userMapper;

    public UserService(UserRepository userRepository, PasswordEncoder encoder, UserMapper userMapper){
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.userMapper = userMapper;
    }

    public UserResponse createUser(UserRequest request){
        User user = userMapper.toUserEntity(request);
        user.setPassword(encoder.encode(request.getPassword()));
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    public List<UserResponse> getUsers(){
        List<User> users = userRepository.findAll();
        return userMapper.toUserResponses(users);
    }

    public void deleteUser(String email){
        User user = userRepository.findUserByEmail(email);
        userRepository.delete(user);
    }

    public UserResponse updateUser(UserRequest request){
        User user = userRepository.findUserByEmail(request.getEmail());
        user.setName(request.getName());
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    public String loginUser(LoginRequest request){
        User user = userRepository.findUserByEmail(request.getUsername());
        String hashPassword = user.getPassword();
        if(encoder.matches(request.getPassword(), hashPassword)
        && request.getUsername().equals(user.getEmail()))
            return user.getGuid();
        return  "Authentication failed";
    }

}

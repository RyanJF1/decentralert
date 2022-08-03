package com.crypt.decentralert.service;

import com.crypt.decentralert.entity.Address;
import com.crypt.decentralert.entity.User;
import com.crypt.decentralert.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class NotificationService {
    private final JavaMailSender javaMailSender;
    private final AddressService addressService;
    private final UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger(AddressService.class);

    public NotificationService(JavaMailSender javaMailSender, AddressService addressService, UserRepository userRepository) {
        this.javaMailSender = javaMailSender;
        this.addressService = addressService;
        this.userRepository = userRepository;
    }

    public void sendEmail(String to, String subject, Object body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(String.valueOf(body));
//        javaMailSender.send(message);
    }

    public void notifyAssetTransfers() {
        User user = userRepository.findUserByEmail("ryan.j.fulton@gmail.com");
        List<String> addresses = new ArrayList<>();
        List<String> hashes = addressService.getAssetTransfers("0x41eAFde086f9D6C66e163e11E6Ad1A39b3CD7818");
        String result = String.join("\n", hashes);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Get Asset Transfers");
        message.setText("Get Asset Transfers for address 0x41eAFde086f9D6C66e163e11E6Ad1A39b3CD7818: \n" + result);
        javaMailSender.send(message);
    }


}

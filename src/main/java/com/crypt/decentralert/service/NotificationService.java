package com.crypt.decentralert.service;

import com.crypt.decentralert.entity.Address;
import com.crypt.decentralert.entity.Notification;
import com.crypt.decentralert.entity.User;
import com.crypt.decentralert.mapper.NotificationMapper;
import com.crypt.decentralert.repository.AddressRepository;
import com.crypt.decentralert.repository.NotificationRepository;
import com.crypt.decentralert.repository.UserRepository;
import com.crypt.decentralert.request.CreateNotificationRequest;
import com.crypt.decentralert.response.NotificationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class NotificationService {
    private final JavaMailSender javaMailSender;
    private final AddressService addressService;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final NotificationRepository notificationRepository;

    private final NotificationMapper notificationMapper;

    Logger logger = LoggerFactory.getLogger(AddressService.class);

    public NotificationService(JavaMailSender javaMailSender, AddressService addressService, UserRepository userRepository, AddressRepository addressRepository, NotificationRepository notificationRepository, NotificationMapper notificationMapper) {
        this.javaMailSender = javaMailSender;
        this.addressService = addressService;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }

    public void createNotification(CreateNotificationRequest request) {
        User user = userRepository.findUserByEmail(request.getEmail());
        Address address = addressRepository.findByAddressId(request.getAddressId());
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setAddress(address);
        notification.setNotify(true);
        user.getNotifications().add(notification);
        notificationRepository.save(notification);
    }


    public List<NotificationResponse> getNotifications(String email) {
        User user = userRepository.findUserByEmail(email);
        List<Notification> notifications = user.getNotifications();
        if(notifications.isEmpty())
            return Collections.emptyList();
        return notificationMapper.toNotificationResponses(notifications);
    }

    public void deleteNotification(long notificationId){
        notificationRepository.deleteById(notificationId);
    }


    public void notifyAssetTransfers() {
        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            SimpleMailMessage[] messages =
            user.getNotifications().stream().map(notification -> {
                String addressId = notification.getAddress().getAddressId();
                List<String> hashes = addressService.getAssetTransfers(addressId);
                String result = String.join("\n\n", hashes);
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(user.getEmail());
                message.setSubject("Get Asset Transfers for address " + notification.getAddress().getNickname() + " [" + addressId + "]");
                message.setText("Get Asset Transfers for address " + addressId + ": \n\n" + result);
                return message;
            }).toArray(SimpleMailMessage[]::new);
            logger.info("Sending email to " + user.getEmail() + "...");
            javaMailSender.send(messages);
        });

    }


}

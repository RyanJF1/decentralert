package com.crypt.decentralert.service;

import com.crypt.decentralert.entity.Address;
import com.crypt.decentralert.entity.Notification;
import com.crypt.decentralert.entity.User;
import com.crypt.decentralert.mapper.NotificationMapper;
import com.crypt.decentralert.repository.AddressRepository;
import com.crypt.decentralert.repository.NotificationRepository;
import com.crypt.decentralert.repository.UserRepository;
import com.crypt.decentralert.request.CreateNotificationRequest;
import com.crypt.decentralert.response.GetAssetTransfersResponse;
import com.crypt.decentralert.response.NotificationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;


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
        if (null != address) {
            Notification notification = new Notification();
            notification.setAddress(address);
            notification.setNotify(true);
            notification.setLastSent(Instant.now().toString());
            notification.setGuid(UUID.randomUUID().toString());
            user.getNotifications().add(notification);
            notificationRepository.save(notification);
        }
    }

    public List<NotificationResponse> getNotifications(String email) {
        User user = userRepository.findUserByEmail(email);
        List<Notification> notifications = user.getNotifications();
        if (notifications.isEmpty())
            return Collections.emptyList();
        return notificationMapper.toNotificationResponses(notifications);
    }

    public void deleteNotification(String guid) {
        Notification notification = notificationRepository.findByGuid(guid);
        notificationRepository.delete(notification);
    }

    public void disableNotification(String guid) {
        Notification notification = notificationRepository.findByGuid(guid);
        notification.setNotify(!notification.isNotify());
        notificationRepository.save(notification);
    }

    public void notifyAssetTransfers() {
        List<User> users = userRepository.findAll();
        List<String> hashes = new ArrayList<>();
        users.forEach(user -> {
            user.getNotifications().stream()
                    .filter(Notification::isNotify).forEach(notification -> {
                String addressId = notification.getAddress().getAddressId();
                AtomicBoolean found = new AtomicBoolean(false);
                GetAssetTransfersResponse response = addressService.getAssetTransfers(addressId);
                logger.info("Getting transfers for address " + addressId);

                if (null != response) {
                    response.getResult().getTransfers().forEach(transfer -> {
                        Instant instant = Instant.parse(transfer.getMetadata());
                        Instant lastSent = Instant.parse(notification.getLastSent());
                        if (instant.isAfter(lastSent)) {
                            logger.info("Transaction found!");
                            found.set(true);
                            hashes.add(transfer.getHash() + " Time: " + instant);
                        }
                    });
                }
                String result = String.join("\n\n", hashes);
                if (found.get()) {
                    SimpleMailMessage message = buildMessage(user, notification, result);
                    javaMailSender.send(message);
                    logger.info("Sent email to " + user.getEmail());
                    notification.setLastSent(Instant.now().toString());
                }
            });
            notificationRepository.saveAll(user.getNotifications());
        });
    }

    private SimpleMailMessage buildMessage(User user, Notification notification, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Get Asset Transfers for address: " + notification.getAddress().getNickname() + " [" + notification.getAddress().getAddressId() + "]");
        message.setText(body);

        return message;
    }

}

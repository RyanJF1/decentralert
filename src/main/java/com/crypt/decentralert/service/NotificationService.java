package com.crypt.decentralert.service;

import com.crypt.decentralert.entity.Address;
import com.crypt.decentralert.entity.Notification;
import com.crypt.decentralert.entity.History;
import com.crypt.decentralert.entity.User;
import com.crypt.decentralert.mapper.NotificationMapper;
import com.crypt.decentralert.repository.AddressRepository;
import com.crypt.decentralert.repository.HistoryRepository;
import com.crypt.decentralert.repository.NotificationRepository;
import com.crypt.decentralert.repository.UserRepository;
import com.crypt.decentralert.request.CreateNotificationRequest;
import com.crypt.decentralert.response.GetAssetTransfersResponse;
import com.crypt.decentralert.response.HistoryResponse;
import com.crypt.decentralert.response.NotificationResponse;
import com.crypt.decentralert.response.TransfersResultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;


@Service
public class NotificationService {
    private final JavaMailSender javaMailSender;
    private final AddressService addressService;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final NotificationRepository notificationRepository;
    private final HistoryRepository historyRepository;

    private final NotificationMapper notificationMapper;

    Logger logger = LoggerFactory.getLogger(AddressService.class);

    public NotificationService(JavaMailSender javaMailSender, AddressService addressService, UserRepository userRepository, AddressRepository addressRepository, NotificationRepository notificationRepository, HistoryRepository historyRepository, NotificationMapper notificationMapper) {
        this.javaMailSender = javaMailSender;
        this.addressService = addressService;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.notificationRepository = notificationRepository;
        this.historyRepository = historyRepository;
        this.notificationMapper = notificationMapper;
    }

    public void createNotification(String guid, CreateNotificationRequest request) {
        User user = userRepository.findUserByGuid(guid);
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

    public List<NotificationResponse> getNotifications(String guid) {
        User user = userRepository.findUserByGuid(guid);
        List<Notification> notifications = user.getNotifications();
        if (notifications.isEmpty())
            return Collections.emptyList();
        return notificationMapper.toNotificationResponses(notifications);
    }

    public List<HistoryResponse> getHistory(String guid) {
        Notification notification = notificationRepository.findByGuid(guid);
        List<History> history = notification.getHistory();
        if (history.isEmpty())
            return Collections.emptyList();
        return notificationMapper.toHistoryResponses(history);
    }

    public List<HistoryResponse> getAllHistory(String guid) {
        User user = userRepository.findUserByGuid(guid);

        List<History> history = new ArrayList<>();
        user.getNotifications().forEach(notification -> {
            history.addAll(notification.getHistory());
        });
        if (history.isEmpty())
            return Collections.emptyList();
        return notificationMapper.toHistoryResponses(history);
    }

    public void deleteNotification(String guid) {
        Notification notification = notificationRepository.findByGuid(guid);
        notificationRepository.delete(notification);
    }

    public List<NotificationResponse> disableNotification(String guid) {
        Notification notification = notificationRepository.findByGuid(guid);
        notification.setNotify(!notification.isNotify());
        notificationRepository.save(notification);
        return notificationMapper.toNotificationResponses(Collections.singletonList(notification));
    }

    public void notifyMultiAssetTransfers() {
        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            user.getNotifications().stream()
                    .filter(Notification::isNotify).forEach(notification -> {
                        String addressId = notification.getAddress().getAddressId();
                        logger.info("Getting transfers for address " + addressId);
                        GetAssetTransfersResponse response = addressService.getAssetTransfers(addressId);

                        narrowByTime(response, notification.getLastSent());

                        if (!response.getResult().getTransfers().isEmpty()) {
                            SimpleMailMessage message = buildMessage(user, notification, response);
                            javaMailSender.send(message);
                            logger.info("Sent email to " + user.getEmail());
                            notification.setLastSent(Instant.now().toString());
                            setHistory(notification, notification.getLastSent());
                        }

                    });
            notificationRepository.saveAll(user.getNotifications());
        });
    }
    private SimpleMailMessage buildMessage(User user, Notification notification, GetAssetTransfersResponse body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Get Asset Transfers for address: " + notification.getAddress().getNickname() + " [" + notification.getAddress().getAddressId() + "]");
        List<String> list = new ArrayList<>();
        for (TransfersResultResponse transfersResultResponse : body.getResult().getTransfers()) {
            String hash = transfersResultResponse.getHash();
            list.add(hash);
        }
        message.setText(String.valueOf(list));

        return message;
    }

    private void setHistory(Notification notification, String lastSent) {
        History history = new History();
        history.setLastSent(lastSent);
        history.setNotification(notification);
        notification.getHistory().add(history);
        historyRepository.save(history);
    }

    private void narrowByTime(GetAssetTransfersResponse response, String time){
        List<TransfersResultResponse> transfers = new ArrayList<>();
        response.getResult().getTransfers().forEach(transfer -> {
            Instant instant = Instant.parse(transfer.getMetadata());
            Instant lastSent = Instant.parse(time);
            if(instant.isAfter(lastSent))
                transfers.add(transfer);
        } );
        response.getResult().setTransfers(transfers);
    }

}

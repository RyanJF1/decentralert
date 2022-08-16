package com.crypt.decentralert.service;

import com.crypt.decentralert.entity.Address;
import com.crypt.decentralert.entity.Notification;
import com.crypt.decentralert.entity.User;
import com.crypt.decentralert.repository.AddressRepository;
import com.crypt.decentralert.repository.NotificationRepository;
import com.crypt.decentralert.repository.UserRepository;
import com.crypt.decentralert.response.DashboardResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UIService {

    private final NotificationRepository notificationRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public UIService(NotificationRepository notificationRepository, AddressRepository addressRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    public DashboardResponse getDashboard(){
        DashboardResponse response = new DashboardResponse();
        List< Notification> notificationList = notificationRepository.findAll();
        List<Address> addressList = addressRepository.findAll();
        List<User> userList = userRepository.findAll();
        long notificationCount = notificationList.size();
        long addressCount = addressList.size();
        long userCount = userList.size();
        response.setAddressCount(addressCount);
        response.setNotificationCount(notificationCount);
        response.setUserCount(userCount);
        return response;
    }
}

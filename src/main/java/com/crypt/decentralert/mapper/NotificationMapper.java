package com.crypt.decentralert.mapper;

import com.crypt.decentralert.entity.History;
import com.crypt.decentralert.entity.Notification;
import com.crypt.decentralert.entity.User;
import com.crypt.decentralert.request.UserRequest;
import com.crypt.decentralert.response.HistoryResponse;
import com.crypt.decentralert.response.NotificationResponse;
import com.crypt.decentralert.response.UserResponse;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Collectors;

public class NotificationMapper {

    @Bean
    public NotificationMapper notificationMapper() {
        return new NotificationMapper();
    }

    public List<NotificationResponse> toNotificationResponses(List<Notification> notifications){
        return notifications.stream().map(notification -> {
            NotificationResponse response = new NotificationResponse();
            response.setNickname(notification.getAddress().getNickname());
            response.setGuid(notification.getGuid());
            response.setAddressId(notification.getAddress().getAddressId());
            response.setNotify(notification.isNotify());
            return response;
        }).collect(Collectors.toList());
    }

    public List<HistoryResponse> toHistoryResponses(List<History> histories){
        return histories.stream().map(history -> {
            HistoryResponse response = new HistoryResponse();
            response.setAddressId(history.getNotification().getAddress().getAddressId());
            response.setLastSent(history.getLastSent());
            return response;
        }).collect(Collectors.toList());
    }
}

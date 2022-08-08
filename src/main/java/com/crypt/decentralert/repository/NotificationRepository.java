package com.crypt.decentralert.repository;

import com.crypt.decentralert.entity.Address;
import com.crypt.decentralert.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}

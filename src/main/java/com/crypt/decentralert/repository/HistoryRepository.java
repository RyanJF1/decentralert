package com.crypt.decentralert.repository;

import com.crypt.decentralert.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {

    List<History> findAllByNotificationId(String notificationGuid);
}

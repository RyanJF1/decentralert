package com.crypt.decentralert.response;

import java.io.Serializable;

public class DashboardResponse implements Serializable {
    private long notificationCount;

    public DashboardResponse() {
    }

    public long getNotificationCount() {
        return notificationCount;
    }

    public void setNotificationCount(long notificationCount) {
        this.notificationCount = notificationCount;
    }

    public long getAddressCount() {
        return addressCount;
    }

    public void setAddressCount(long addressCount) {
        this.addressCount = addressCount;
    }

    private long addressCount;

    public long getUserCount() {
        return userCount;
    }

    public void setUserCount(long userCount) {
        this.userCount = userCount;
    }

    private long userCount;
}

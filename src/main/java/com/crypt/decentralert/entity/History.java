package com.crypt.decentralert.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "history")
public class History implements Serializable {

    static final long serialVersionUID = 1L;


    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    @ManyToOne
    private
    Notification notification;

    public String getLastSent() {
        return lastSent;
    }

    public void setLastSent(String lastSent) {
        this.lastSent = lastSent;
    }

    @JsonProperty("last_sent")
private String lastSent;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}

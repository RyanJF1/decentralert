package com.crypt.decentralert.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "notification")
public class Notification implements Serializable {

    static final long serialVersionUID = 1L;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @OneToOne
private Address address;
private boolean notify;
@JsonProperty("type")
private String type;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    private String guid;

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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public boolean isNotify() {
        return notify;
    }

    public void setNotify(boolean notify) {
        this.notify = notify;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

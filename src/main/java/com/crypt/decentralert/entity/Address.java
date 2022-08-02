package com.crypt.decentralert.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "address")
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;

    public int getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;


    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    @NaturalId
    @Column(name = "address_id", unique=true)
    private String addressId;
    @Column(name = "nickname")
    private String nickname;

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }



}

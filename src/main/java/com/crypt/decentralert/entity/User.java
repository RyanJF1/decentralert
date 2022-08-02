package com.crypt.decentralert.entity;

import org.springframework.scheduling.annotation.EnableScheduling;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements Serializable {

    static final long serialVersionUID = 1L;

    private String name;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @OneToMany
    private List<Address> addresses;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

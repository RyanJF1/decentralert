package com.crypt.decentralert.repository;

import com.crypt.decentralert.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findByAddressId(@Param("adsress_id") String addressId);
}

package com.crypt.decentralert.repository;

import com.crypt.decentralert.entity.Address;
import com.crypt.decentralert.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);
    User findUserByGuid(String guid);

}

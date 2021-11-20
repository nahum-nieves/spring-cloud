package com.yp.challenge.repositories;

import com.yp.challenge.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends org.springframework.data.repository.Repository<User,Integer> {
    public Optional<User> findByUsername(String username);
}

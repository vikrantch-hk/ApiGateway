package com.hk.gateway.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hk.gateway.pojo.User;

public interface UserRepo extends JpaRepository<User, Long> {

}

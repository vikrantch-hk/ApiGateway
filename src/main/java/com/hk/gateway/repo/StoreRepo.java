package com.hk.gateway.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hk.gateway.pojo.Store;

public interface StoreRepo extends JpaRepository<Store, Long> {

}

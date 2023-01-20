package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}

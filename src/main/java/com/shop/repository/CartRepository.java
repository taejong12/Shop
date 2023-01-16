package com.shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

//	Page<CartEntity> findByMemberNo(Pageable pageable);

}

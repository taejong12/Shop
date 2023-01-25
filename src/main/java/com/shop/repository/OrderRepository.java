package com.shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entity.MemberEntity;
import com.shop.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

	// 회원 넘버 주문내역
	Page<OrderEntity> findByMemberEntity(MemberEntity memberEntity, Pageable pageable);

}

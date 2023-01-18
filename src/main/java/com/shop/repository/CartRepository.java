package com.shop.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entity.CartEntity;
import com.shop.entity.ItemEntity;
import com.shop.entity.MemberEntity;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

	// 멤버 넘버아이템 넘버로 장바구니 중복확인
	CartEntity findByMemberEntityAndItemEntity(MemberEntity memberEntity, ItemEntity itemEntity);

	// 해당하는 멤버 장바구니 출력
	Page<CartEntity> findByMemberEntity(MemberEntity memberEntity, Pageable pageable);


}

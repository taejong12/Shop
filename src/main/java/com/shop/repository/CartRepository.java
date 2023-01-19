package com.shop.repository;

import org.springframework.data.domain.Page;
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

	// 해당 멤버의 카트 넘버 삭제
	void deleteByCartNoAndMemberEntity(Long cartNo, MemberEntity memberEntity);

}

package com.shop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.dto.CartDto;

public interface CartService {

	// 장바구니 등록
	CartDto cartItemSave(CartDto cartDto) throws Exception;

	// 장바구니 중복 확인
	CartDto cartItemCheck(CartDto cartDto) throws Exception;

	// 장바구니 목록
	Page<CartDto> cartPaging(Pageable pageable, Long memberNo) throws Exception;

	// 장바구니 삭제
	void cartDelete(Long cartNo) throws Exception;
	
}

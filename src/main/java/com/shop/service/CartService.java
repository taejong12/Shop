package com.shop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.dto.CartDto;

public interface CartService {

	// 장바구니 등록
	void cartItemSave(CartDto cartDto) throws Exception;

	// 장바구니 목록
//	Page<CartDto> paging(Pageable pageable) throws Exception;

	
}

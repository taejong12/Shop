package com.shop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.dto.CartDto;

public interface CartService {

	// 장바구니 등록
	public CartDto cartItemSave(CartDto cartDto) throws Exception;

	// 장바구니 중복 확인
	public CartDto cartItemCheck(CartDto cartDto) throws Exception;

	// 장바구니 목록
	public Page<CartDto> cartPaging(Pageable pageable, Long memberNo) throws Exception;

	// 장바구니 삭제
	public void cartDelete(Long cartNo, Long memberNo) throws Exception;

	// 장바구니 일괄 삭제
	public void cartCheckDelete(Long cartNo, Long memberNo) throws Exception;

	// 장바구니 수량 수정
	public void cartItemAmountModify(CartDto cartDto) throws Exception;

}

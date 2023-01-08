package com.shop.service;

import java.util.List;

import com.shop.dto.CartDTO;

public interface CartService {
	/* 장바구니 추가 */
	public int addCart(CartDTO cartDto);
	/* 장바구니 불러오기 */
	public List<CartDTO> getCartList(String username);
	/* 수량 수정 */
	public int modifyCount(CartDTO cart);
	/* 장바구니 삭제 */
	public int deleteCart(int caNum);
}	


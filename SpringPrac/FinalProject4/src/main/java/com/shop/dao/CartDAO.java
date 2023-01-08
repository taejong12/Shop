package com.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shop.dto.CartDTO;

@Mapper
public interface CartDAO {
	/* 카트 추가 */
	public int addCart(CartDTO cartDto) throws Exception;
	
	/* 카트 삭제 */
	public int deleteCart(int caNum);
	
	/* 카트 수량 수정 */
	public int modifyCount(CartDTO cartDto);
	
	/* 카트 목록 */
	public List<CartDTO> getCart(String id);	
	
	/* 카트 확인 */
	public CartDTO checkCart(CartDTO cartDto);
}

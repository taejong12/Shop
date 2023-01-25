package com.shop.service;

import java.util.List;

import com.shop.dto.OrderDto;
import com.shop.dto.OrderListDto;

public interface OrderService {

	// 장바구니 상품 결제 페이지
	List<OrderDto> cartItemOrderList(List<OrderDto> orderDto) throws Exception;

	// 주문하기 회원정보
	OrderDto memberInfo(Long memberNo) throws Exception;

	// 결제완료
	void orderSave(Long memberNo, List<OrderDto> orders) throws Exception;

	
}

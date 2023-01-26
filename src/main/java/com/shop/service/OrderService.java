package com.shop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.dto.CartDto;
import com.shop.dto.ItemDto;
import com.shop.dto.OrderDto;

public interface OrderService {

	// 장바구니 상품 결제 페이지
	public List<OrderDto> cartItemOrderList(List<OrderDto> orderDto) throws Exception;

	// 결제페이지 회원정보
	public OrderDto memberInfo(Long memberNo) throws Exception;

	// 결제완료(장바구니)
	public void orderSave(Long memberNo, List<OrderDto> orders) throws Exception;

	// 주문내역 목록
	public Page<OrderDto> orderPaging(Pageable pageable, Long memberNo) throws Exception;

	// 결제 취소
	public void orderCancel(OrderDto orderDto) throws Exception;

	// 상품 상세보기 결제 페이지
	public OrderDto itemDetailOrderList(OrderDto orderDto) throws Exception;
	
}

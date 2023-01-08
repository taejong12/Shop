package com.shop.service;

import java.util.List;
import java.util.Map;

import com.shop.dto.OrderItemDTO;

public interface OrderService {
//
//	//	장바구니에 담긴 금액과 db의 금액이 같은지 확인
//	long orderPriceCheck(CartList cartList);
	/* 주문 정보 */
	public List<OrderItemDTO> getGoodsInfo(List<OrderItemDTO> orders);
	
	public List<OrderItemDTO> getOrderList(String username);
	public int orderPage(Map<String,String> map);
	public int orderInsert(Map<String, String> map);
	public OrderItemDTO orderPage(String username);
	
}

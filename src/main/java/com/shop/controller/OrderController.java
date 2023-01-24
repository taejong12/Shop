package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.dto.OrderDto;

@Controller
@RequestMapping("/order")
public class OrderController {

	// 주문내역 목록
	@GetMapping("/list/{memberNo}")
	public String cartList() {
		return "/order/list";
	}
	
	// 상품 상세보기 결제페이지
	@GetMapping("/item/{itemNo}")
	public String orderItem(@PathVariable("itemNo") Long itemNo) {
		
		return "/order/item";
	}
	
	// 장바구니 결제페이지
	@GetMapping("/cartItem/{memberNo}")
	public String orderCartItem(@PathVariable("memberNo") Long memberNo, Model model, OrderDto orderDto) {
		
		return "/order/cartItem";
	}
	
	// 결제완료 페이지
	@GetMapping("/success")
	public String orderSuccess() {
		
		return "/order/success";
	}
}
package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

	@GetMapping("/list/{memberNo}")
	public String cartList() {
		return "/order/list";
	}
	
	@GetMapping("/item/{boardNo}")
	public String orderItem(@PathVariable("boardNo") Long boardNo) {
		
		return "/order/item";
	}
	
	@GetMapping("/cartItem")
	public String orderCartItem() {
		
		return "/order/cartItem";
	}
	
	@GetMapping("/success")
	public String orderSuccess() {
		
		return "/order/success";
	}
}

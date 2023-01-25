package com.shop.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.dto.CartDto;
import com.shop.dto.OrderDto;
import com.shop.dto.OrderListDto;
import com.shop.service.MemberService;
import com.shop.service.OrderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

	private final OrderService orderService;
	
	// 주문내역 목록
	@GetMapping("/list/{memberNo}")
	public String cartList(@PageableDefault(page = 1) Pageable pageable, Model model, @PathVariable("memberNo") Long memberNo) throws Exception {
		
		Page<OrderDto> orderList =orderService.orderPaging(pageable, memberNo); 
		int blockLimit =3; 
		int startPage =(((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; 
		int endPage = ((startPage + blockLimit - 1) < orderList.getTotalPages()) ? startPage + blockLimit - 1 : orderList.getTotalPages();
		
		model.addAttribute("orderList", orderList); 
		model.addAttribute("startPage", startPage); 
		model.addAttribute("endPage", endPage); 
		return "/order/list";
	}
	
	// 상품 상세보기 결제페이지
	@GetMapping("/item/{itemNo}")
	public String orderItem(@PathVariable("itemNo") Long itemNo) {
		
		return "/order/item";
	}
	
	// 장바구니 결제페이지
	@GetMapping("/cartItem/{memberNo}")
	public String orderCartItem(@PathVariable("memberNo") Long memberNo, Model model, OrderListDto orders) throws Exception {
		
		System.out.println("memberNo : "+memberNo);
		System.out.println("orders : "+orders.getOrders());
		
		// 상품 목록
		model.addAttribute("cartOrderList", orderService.cartItemOrderList(orders.getOrders()));
		
		System.out.println("cartOrderList : "+ orderService.cartItemOrderList(orders.getOrders()));
		
		// 회원 정보
		model.addAttribute("memberInfo", orderService.memberInfo(memberNo));
	
		return "/order/cartItem";
	}
	
	// 결제완료 페이지
	@GetMapping("/success/{memberNo}")
	public String orderSuccess(@PathVariable("memberNo") Long memberNo, OrderListDto orders) throws Exception {
		
		System.out.println("orderDto : "+orders);
		
		orderService.orderSave(memberNo, orders.getOrders());
		
		return "/order/success";
	}
}
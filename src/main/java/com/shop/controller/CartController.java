package com.shop.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shop.dto.CartDto;
import com.shop.dto.ItemDto;
import com.shop.service.CartService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
	
	private final CartService cartService;
	
	// 장바구니 목록
	@GetMapping("/list/{memberNo}")
	public String paging(/* @PageableDefault(page = 1) Pageable pageable, Model model */) throws Exception {
		/*
		 * //pageable.getPageNumber(); Page<CartDto> cartList =
		 * cartService.paging(pageable); int blockLimit =3; int startPage =
		 * (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) *
		 * blockLimit + 1; int endPage = ((startPage + blockLimit - 1) <
		 * cartList.getTotalPages()) ? startPage + blockLimit - 1 :
		 * cartList.getTotalPages();
		 * 
		 * model.addAttribute("cartList", cartList); model.addAttribute("startPage",
		 * startPage); model.addAttribute("endPage", endPage); //
		 * System.out.println("itemList : ======" + itemList.getContent());
		 */		
		return "/cart/list";
		
	}
	@PostMapping("/item")
	public @ResponseBody void cartItem(@ModelAttribute CartDto cartDto) throws Exception {
		System.out.println(cartDto);
		cartService.cartItemSave(cartDto);
	}
	

	
	
	
}
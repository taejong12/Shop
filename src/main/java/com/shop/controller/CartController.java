package com.shop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.dto.CartDto;

import com.shop.service.CartService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
	
	private final CartService cartService;
	
	// 장바구니 목록
	@GetMapping("/list/{memberNo}")
	public String paging(@PageableDefault(page = 1) Pageable pageable, Model model, @PathVariable("memberNo") Long memberNo) throws Exception {
		
		Page<CartDto> cartList =cartService.cartPaging(pageable, memberNo); 
		int blockLimit =3; 
		int startPage =(((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; 
		int endPage = ((startPage + blockLimit - 1) < cartList.getTotalPages()) ? startPage + blockLimit - 1 : cartList.getTotalPages();
		
		model.addAttribute("cartList", cartList); 
		model.addAttribute("startPage", startPage); 
		model.addAttribute("endPage", endPage); 
 		
		return "/cart/list";
		
	}

	// 장바구니 추가
	@PostMapping("/item")
	public @ResponseBody CartDto cartItem(@ModelAttribute CartDto cartDto) throws Exception {
		System.out.println("cartItem : "+cartDto);
		CartDto cartData = cartService.cartItemSave(cartDto);
		
		return cartData;
		
	}
	
	// 장바구니 중복 체크
	@PostMapping("/check")
	public @ResponseBody CartDto cartItemCheck(@ModelAttribute CartDto cartDto) throws Exception {
		System.out.println("cartItemCheck : "+cartDto);
		CartDto cartData = cartService.cartItemCheck(cartDto);
		
		return cartData;
		
	}
	
	// 장바구니 삭제
	@GetMapping("/delete/{cartNo}")
	public String cartItemDelete(@PathVariable("cartNo") Long cartNo, RedirectAttributes ra, HttpSession session) throws Exception {
		Long memberNo = (Long) session.getAttribute("memberNo");
		cartService.cartDelete(cartNo, memberNo);
		
		// 장바구니 삭제 메시지
		ra.addFlashAttribute("msg", "cartDeleteSuccess");
		return "redirect:/cart/list/"+session.getAttribute("memberNo");
		
	}
		
	
}
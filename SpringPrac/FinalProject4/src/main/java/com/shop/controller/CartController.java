package com.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shop.dto.CartDTO;
import com.shop.login.LoginService;
import com.shop.service.CartService;

@Controller
public class CartController {
	@Autowired
	private CartService service;
	
	@PostMapping("/cart/add")
	@ResponseBody
	public String addCartPOST(CartDTO cartDto,@AuthenticationPrincipal LoginService user) {
		// 로그인 체크
		String username = user.getUser().getUsername();
		if(username == null) {
			return "5";
		}
		
		// 카트 등록
		int result = service.addCart(cartDto);
		
		return result + "";
	}
	
	@GetMapping("/cart/{username}")
	public String cartPageGET(@PathVariable("username") String username, Model model) {
		
		model.addAttribute("cartInfo", service.getCartList(username));
		
		return "/cart/cart";
	} 
	
	/* 장바구니 수량 수정 */
	@PostMapping("/cart/update")
	public String updateCartPOST(CartDTO cart) {
		
		service.modifyCount(cart);
		
		
		return "redirect:/cart/"+cart.getId();
		
	}
	
	/* 장바구니 삭제 */
	@PostMapping("/cart/delete")
	public String deleteCartPOST(CartDTO cart) {
	      
	    service.deleteCart(cart.getCaNum());
	      
	    return "redirect:/cart/"+cart.getId();
	      
	}     
}

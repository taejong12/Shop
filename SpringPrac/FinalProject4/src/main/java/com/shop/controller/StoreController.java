package com.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.service.StoreService;

@Controller
public class StoreController {
	@Autowired
	StoreService service;
	
	@RequestMapping("/store")
	public String detailView(HttpServletRequest req,Model model) {
		String alKind = req.getParameter("al_kind");
		System.out.println(alKind); //정상
		model.addAttribute("dto",service.list(alKind));
		System.out.println(model.getAttribute("dto"));
		return "store/storeView";
	}
}

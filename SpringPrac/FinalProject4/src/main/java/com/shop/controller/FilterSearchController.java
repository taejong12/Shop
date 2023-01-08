package com.shop.controller;





import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.shop.dto.FilterCriteriaDTO;
import com.shop.dto.FilterPageMakeDTO;
import com.shop.service.FilterSearchService;

@Controller
public class FilterSearchController {
		
	@Autowired
	private FilterSearchService service;
	
	@GetMapping("/store/search")
	public String boardListGET(Model model, FilterCriteriaDTO cri) {
		
		model.addAttribute("list", service.getListPaging(cri));
		
		int total = service.getTotal(cri);
	        
        FilterPageMakeDTO pageMake = new FilterPageMakeDTO(cri, total);
        
        model.addAttribute("pageMaker", pageMake);

		return "/FilterSearch/list";
	}
	

	
}

	
	
	
	
	
	
	
	
	

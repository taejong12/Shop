package com.shop.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.dto.ItemDto;
import com.shop.service.ItemServiceImpl;

import lombok.RequiredArgsConstructor;

// 의존성주입(DI), 생성자에 의존성을 주입해주는 롬복 어노테이션, 전에는 @Autowired를 통해 필드에 의존성 주입을 받아왔다.
// final이 붙은 필드의 생성자를 자동 생성해준다.
@RequiredArgsConstructor

// 자바 로직 수행
@Controller
@RequestMapping("/item")
public class ItemController {

	private final ItemServiceImpl itemService;
	
//	//상품 목록
//	@GetMapping("/list")
//	public String itemList(Model model) throws Exception {
//		// DB에 저장되어 있는 모든 데이터를 가져온다
//		// 가져오니까 리턴을 받아야 하는데 한개가 아니라 여러개니까 리스트로 받는다
//		// 어떠한 html로 가져갈 데이터가 있다면 model 사용, 다른 방식도 있음 
//		// DB에서 전체 게시글 데이터를 가져와서 list.jsp에 보여준다.
//		List<itemDto> itemDtoList = itemService.itemListFindAll();
//		model.addAttribute("itemList", itemDtoList);
//		System.out.println("itemList : ======" + itemDtoList);
//		return "item/list";
//	}
	
	// 상품 작성 페이지 이동
	@GetMapping("/write")
	public String writeItemForm() {
		return "/item/write";
	}
	
	// 상품 작성 후 저장
	@PostMapping("/write")
	public String writeItemSave(@ModelAttribute ItemDto itemDto,BindingResult bindingResult, RedirectAttributes ra) throws Exception {
		itemService.writeItemSave(itemDto);
		
		// 상품 등록 메시지
		ra.addFlashAttribute("msg", "itemWriteSuccess");
		return "redirect:/item/paging";
	}
	
	// 상품 상세보기 페이지
	@GetMapping(value = "/detail/{itemNo}")
    public String itemDetail(Model model, @PathVariable("itemNo") Long itemNo, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		// 조회수 중복을 방지하기 위해 쿠키에는 상품 번호를 기록한다.
		// 저장된 쿠키 불러오기
		Cookie cookies[] = req.getCookies();
		Map<String, Object> mapCookie = new HashMap<>();
		if(req.getCookies() != null) {
			for(int i=0;i<cookies.length; i++) {
				Cookie obj = cookies[i];
				mapCookie.put(obj.getName(), obj.getValue());
			}
		}
		
		// 저장된 쿠키중에 itemHits 만 불러오기
		String cookie_item_hits =(String) mapCookie.get("item_hits");
		
		// 저장될 새로운 쿠키값 생성
		String new_cookie_item_hits = "|"+itemNo;
		
		// 저장된 쿠키에 새로운 쿠키값이 존재하는 지 검사
		if(StringUtils.indexOfIgnoreCase(cookie_item_hits, new_cookie_item_hits)==-1) {
			
			// 쿠키 없을 경우 쿠키 생성
			Cookie cookie = new Cookie("item_hits", cookie_item_hits + new_cookie_item_hits);
			
			// cookie.setMaxAge(1000); // 초단위
			cookie.setMaxAge(60);
			
			res.addCookie(cookie);
			
			// 조회수 업데이트
			// 해당 게시글의 조회수를 하나 올리고 게시글 데이터를 가져와서 detail.jsp에 출력
			itemService.itemUpdateHits(itemNo);
			
		}
		
		
		ItemDto itemDto = itemService.itemDetailFindById(itemNo);
		model.addAttribute("itemDetailList", itemDto);
		//System.out.println("itemDetailList =====:" + itemDto);
        return "/item/detail";
    }
	
	// 상품 수정 페이지 이동
	@GetMapping("/update/{itemNo}")
	public String updateitem(@PathVariable("itemNo") Long itemNo, Model model) throws Exception {
		ItemDto itemDto = itemService.itemDetailFindById(itemNo);
		model.addAttribute("itemUpdate", itemDto);
		//System.out.println("itemUpdate : "+ itemDto);
		return "/item/update";
	}
	
	// 상품 수정 후 저장
	@PostMapping("/update")
	public String updateitem(@ModelAttribute ItemDto itemDto, Model model, RedirectAttributes ra) throws Exception {
		//System.out.println("itemDto : "+ itemDto);
		itemService.itemUpdate(itemDto);
		
		// 상품 수정 메시지
		ra.addFlashAttribute("msg", "itemUpdateSuccess");
		return "redirect:/item/detail/"+itemDto.getItemNo();
	}
	
	// 상품 삭제
	@GetMapping("/delete/{itemNo}")
	public String deleteitem(@PathVariable Long itemNo, RedirectAttributes ra) throws Exception {
		itemService.itemDelete(itemNo);
		
		// 상품 삭제 메시지
		ra.addFlashAttribute("msg", "itemDeleteSuccess");
		return "redirect:/item/paging";
	}
	
	// /item/paging?page=1
	@GetMapping("/paging")
	public String paging(@PageableDefault(page = 1) Pageable pageable, Model model, 
						@RequestParam(required = false, defaultValue = "") String searchText) throws Exception {
		//pageable.getPageNumber();
		Page<ItemDto> itemList = itemService.paging(pageable, searchText);
		int blockLimit =3;
		int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
		int endPage = ((startPage + blockLimit - 1) < itemList.getTotalPages()) ? startPage + blockLimit - 1 : itemList.getTotalPages();
		
		model.addAttribute("itemList", itemList);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		// System.out.println("itemList : ======" + itemList.getContent());
		return "/item/paging";
		
	}
	
}

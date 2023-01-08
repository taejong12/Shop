package com.shop.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.pagemodel.Criteria;
import com.shop.service.NoticeService;

@Controller
public class NoticeController {
	
	private static final Logger log = LoggerFactory.getLogger(NoticeController.class);

	@Autowired
	NoticeService service;

	@RequestMapping("/notice/write")
	public String writeForm(HttpServletRequest req) {
		return "/notice/writeForm";
	}

	@RequestMapping("/notice/writing")
	public String write(HttpServletRequest req, Model model) {
		String tit = req.getParameter("title");
		String con = req.getParameter("content");
		String wri = req.getParameter("writer");

		System.out.println("아이디 값은" + req.getParameter("id"));
		Map<String, String> map = new HashMap<String, String>();
		map.put("title", tit);
		map.put("content", con);
		map.put("writer", wri);
		int res = service.noticeWriteDao(map);
		System.out.println("write:" + res);
		return "redirect:/notice/list";
	}

	@RequestMapping("/notice/update")
	public String update(HttpServletRequest req, Model model) {

		String id = req.getParameter("id");
		String tit = req.getParameter("title");
		String con = req.getParameter("content");
		String wri = req.getParameter("writer");
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("title", tit);
		map.put("content", con);
		map.put("writer", wri);
		System.out.println("업데이트 값 보기");

		System.out.println("id는" + id);
		System.out.println("tit는" + tit);
		System.out.println("con는" + con);
		System.out.println("wri는" + wri);

		int res = service.noticeUpdateDao(map);

		System.out.println("update:" + res);
		return "redirect:/notice/list";
	}

	@RequestMapping("/notice/updateView")
	public String updateView(HttpServletRequest req, Model model) {
		String uId = req.getParameter("id");
		model.addAttribute("dto", service.noticeViewDao(uId)); // dto설정 - dao의 viewDao에 uId를 넣음
		return "/notice/updateForm"; // update.jsp로 이동
	}

	@RequestMapping("/notice/delete")
	public String delete(HttpServletRequest req, Model model) {
		int res = service.noticeDeleteDao(req.getParameter("id"));
		System.out.println("delete:" + res);
		return "redirect:/notice/list";
	}

	@RequestMapping("/notice/view")
	public String detailView(HttpServletRequest req, Model model) {
		String uId = idk;
		System.out.println("view 아이디는" + uId);
		model.addAttribute("dto", service.noticeViewDao(uId));
		return "/notice/view";
	}

	 @RequestMapping("/notice/list") 
	 public String userlist(Model model) {
	 System.out.println("리스트 출력"); 
	 model.addAttribute("list",service.noticeListDao());
	 return "/notice/noticeList"; 
	 
	 }

	String idk;

	@RequestMapping("/notice/view2")
	public String visitCount(HttpServletRequest req, Model model) {
		String vId = req.getParameter("id");
		idk = vId;
		System.out.println("view2 id는" + vId);
		model.addAttribute("dto", service.noticeVisitCount(vId));
		return "redirect:/notice/view";
	}
	
	 @GetMapping("/notice/list1")
	    public String boardListGET(Model model, Criteria cri) {
	        log.info("boardListGET");
	        model.addAttribute("list", service.noticeGetListPaging(cri));
	        return "/notice/noticeList";
	 }

}

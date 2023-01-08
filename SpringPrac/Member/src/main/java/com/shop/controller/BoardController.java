package com.shop.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.dto.BoardDto;
import com.shop.service.BoardServiceImpl;

import lombok.RequiredArgsConstructor;

// 의존성주입(DI), 생성자에 의존성을 주입해주는 롬복 어노테이션, 전에는 @Autowired를 통해 필드에 의존성 주입을 받아왔다.
// final이 붙은 필드의 생성자를 자동 생성해준다.
@RequiredArgsConstructor

// 자바 로직 수행
@Controller
@RequestMapping("/board")
public class BoardController {

	private final BoardServiceImpl boardService;
	
	//상품 목록
	@GetMapping("/list")
	public String boardList(Model model) throws Exception {
		// DB에 저장되어 있는 모든 데이터를 가져온다
		// 가져오니까 리턴을 받아야 하는데 한개가 아니라 여러개니까 리스트로 받는다
		// 어떠한 html로 가져갈 데이터가 있다면 model 사용, 다른 방식도 있음 
		// DB에서 전체 게시글 데이터를 가져와서 list.jsp에 보여준다.
		List<BoardDto> boardDtoList = boardService.boardListFindAll();
		model.addAttribute("boardList", boardDtoList); 
		return "board/list";
	}
	
	// 상품 작성 페이지 이동
	@GetMapping("/write")
	public String writeBoardForm() {
		return "/board/write";
	}
	
	// 상품 작성 후 저장
	@PostMapping("/write")
	public String writeBoardSave(@ModelAttribute BoardDto boardDto) throws Exception {
		System.out.println("boardDto"+boardDto);
		boardService.writeBoardSave(boardDto);
		return "redirect:/board/list";
	}
	
	// 상품 상세보기 페이지
	@GetMapping(value = "/detail/{boardNo}")
    public String itemDetail(Model model, @PathVariable("boardNo") Long boardNo) throws Exception {
		// 해당 게시글의 조회수를 하나 올리고 게시글 데이터를 가져와서 detail.jsp에 출력
		boardService.boardUpdateHits(boardNo);
		BoardDto boardDto = boardService.boardDetailFindById(boardNo);
		model.addAttribute("boardDetailList", boardDto);
        return "/board/detail";
    }
	
	// 상품 수정 페이지 이동
	@GetMapping("/update/{boardNo}")
	public String updateBoard() {
		
		return "/board/update";
	}
	
	// 상품 수정 후 저장
	@PostMapping("/update")
	public String updateBoard(BoardDto boardDto) {
		
		return "redirect:/board/list";
	}
	
	// 상품 삭제
	@PostMapping("/delete/{boardNo}")
	public String deleteBoard(@RequestParam List<String> boardIds) {
	
		return "redirect:/board/list";
	}
}

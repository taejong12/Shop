package com.shop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.dto.BoardDto;

public interface BoardService {

	// 상품목록조회
	public List<BoardDto> boardListFindAll() throws Exception;
	
	// 상품등록하기
	public void writeBoardSave(BoardDto boardDto) throws Exception;
	
	// 상품조회수증가
	public void boardUpdateHits(Long boardNo) throws Exception;
	
	// 상품상세보기
	public BoardDto boardDetailFindById(Long boardNo) throws Exception;
	
	// 상품수정하기
	public BoardDto boardUpdate(BoardDto boardDto) throws Exception;
	
	// 상품삭제하기
	public void boardDelete(Long boardNo) throws Exception;
	
	// 상품목록페이징
	public Page<BoardDto> paging(Pageable pageable) throws Exception;
}

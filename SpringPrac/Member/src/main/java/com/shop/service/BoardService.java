package com.shop.service;

import java.util.List;

import com.shop.dto.BoardDto;

public interface BoardService {

	public List<BoardDto> boardListFindAll() throws Exception;
	public void writeBoardSave(BoardDto boardDto) throws Exception;
	public void boardUpdateHits(Long boardNo) throws Exception;
	public BoardDto boardDetailFindById(Long boardNo) throws Exception;
}

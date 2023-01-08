package com.shop.service;

import java.util.List;

import com.shop.dto.BoardDto;

public interface BoardService {

	public List<BoardDto> boardListFindAll() throws Exception;
	
}

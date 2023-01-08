package com.test.crud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.test.crud.dto.BoardDto;
import com.test.crud.entity.BoardEntity;
import com.test.crud.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	// 게시물 작성하기
	@Transactional
	public void saveWrite(BoardDto boardDto) {
		
		BoardEntity write = BoardEntity.builder()
				.title(boardDto.getTitle())
				.content(boardDto.getContent())
				.writer(boardDto.getWriter())
				.build();
				
		boardRepository.save(write);
		
	}
	
	// 게시물 목록
	@Transactional
	public List<BoardDto> getBoardList() {
		List<BoardEntity> boardEntity = boardRepository.findAll();
		List<BoardDto> boardList = new ArrayList<>();
		
		for(BoardEntity boardEntityList : boardEntity ) {
			BoardDto boardDto = BoardDto.builder()
					.id(boardEntityList.getId())
					.title(boardEntityList.getTitle())
					.writer(boardEntityList.getWriter())
					.build();
			boardList.add(boardDto);
		}
		
		return boardList;
	}

	// 게시물 상세보기
	public BoardDto getBoardDetail(Long boardId) {
		BoardEntity board = boardRepository.findById(boardId).orElseThrow();
		BoardDto boardDto = new BoardDto(board.getId(), board.getTitle(), board.getWriter(), board.getContent());
		
		return boardDto;
	}

	
	// 게시물 삭제
	public void boardDelete(Long boardId) {
		boardRepository.deleteById(boardId);
	}

	public void boardUpdate(Long boardId, BoardDto boardDto) {
		
		
		BoardEntity update = BoardEntity.builder()
				.title(boardDto.getTitle())
				.content(boardDto.getContent())
				.writer(boardDto.getWriter())
				.build();
		
		boardRepository.save(update);
		
	}
	
	
	
	
	
	
	
}

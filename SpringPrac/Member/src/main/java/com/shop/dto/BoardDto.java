package com.shop.dto;

import java.time.LocalDateTime;

import com.shop.entity.BoardEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// DTO(Data Transfer Object), VO, Bean

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
	
	private Long boardNo; // 상품넘버
	private String boardImage;//상품이미지
	private String boardTitle; //상품명
	private String boardContent; // 상품설명
	private int boardPrice; //상품가격
	private int boardStock; // 상품재고
	private int boardHits; // 상품조회수
	private LocalDateTime boardCreateDate; // 상품등록일
	private LocalDateTime boardUpdateDate; // 상품수정일
		
	public static BoardDto toBoardDto(BoardEntity boardEntity) {
		BoardDto boardDto = new BoardDto();
		boardDto.setBoardNo(boardEntity.getBoardNo());
		boardDto.setBoardImage(boardEntity.getBoardImage());
		boardDto.setBoardTitle(boardEntity.getBoardTitle());
		boardDto.setBoardContent(boardEntity.getBoardContent());
		boardDto.setBoardPrice(boardEntity.getBoardPrice());
		boardDto.setBoardStock(boardEntity.getBoardStock());
		boardDto.setBoardHits(boardEntity.getBoardHits());
		boardDto.setBoardCreateDate(boardEntity.getCreatedTime());
		boardDto.setBoardUpdateDate(boardEntity.getUpdatedTime());
		
		return boardDto;
	}
	
}

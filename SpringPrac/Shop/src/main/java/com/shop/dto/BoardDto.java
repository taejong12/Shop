package com.shop.dto;

import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

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
	private String boardTitle; //상품명
	private String boardContent; // 상품설명
	private int boardPrice; //상품가격
	private int boardStock; // 상품재고
	private int boardHits; // 상품조회수
	private Date boardCreateDate; // 상품등록일
	private Date boardUpdateDate; // 상품수정일
	
	private MultipartFile boardFile; // jsp -> controller 파일 담는 용도
	private String originalBoardFile; // 원본 파일 이름
	private String storedBoardFile; // 서버 저장용 파일 이름
	private int boardFileAttached; // 이미지 첨부 여부(첨부 1, 미첨부 0)
	
	// 상품목록
	public static BoardDto toBoardDto(BoardEntity boardEntity) {
		BoardDto boardDto = new BoardDto();
		boardDto.setBoardNo(boardEntity.getBoardNo());
		boardDto.setBoardTitle(boardEntity.getBoardTitle());
		boardDto.setBoardContent(boardEntity.getBoardContent());
		boardDto.setBoardPrice(boardEntity.getBoardPrice());
		boardDto.setBoardStock(boardEntity.getBoardStock());
		boardDto.setBoardHits(boardEntity.getBoardHits());
		boardDto.setBoardCreateDate(boardEntity.getCreatedTime());
		boardDto.setBoardUpdateDate(boardEntity.getUpdatedTime());
		if(boardEntity.getBoardFileAttached()==0) { // 이미지 없으면 생략
			boardDto.setBoardFileAttached(boardEntity.getBoardFileAttached()); // 0
		} else {
			boardDto.setBoardFileAttached(boardEntity.getBoardFileAttached()); // 1
			// 파일 이름을 가져가야 함.
			// originalFileName, storedFileName : shop_board_file(BoardFileEntity)
			// join
			// select * from shop_board b, shop_board_file bf where b.board_no = bf.board_no and b.id=?
			boardDto.setOriginalBoardFile(boardEntity.getBoardFileEntityList().get(0).getBoardFileOriginalFileName());
			boardDto.setStoredBoardFile(boardEntity.getBoardFileEntityList().get(0).getBoardFileStoredFileName());
		}
		
		return boardDto;
	}

	// 페이징 상품 목록
	public BoardDto(Long boardNo, String boardTitle, int boardPrice, int boardStock, int boardHits,
			Date date) {
		this.boardNo=boardNo;
		this.boardTitle = boardTitle;
		this.boardPrice = boardPrice;
		this.boardStock = boardStock;
		this.boardHits = boardHits;
		this.boardCreateDate = date;
	}
	
	
	
}

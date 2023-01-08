package com.shop.service;


import org.springframework.stereotype.Service;

import com.shop.dto.BoardDto;
import com.shop.entity.BoardEntity;
import com.shop.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor

// JPA에서 제공하는 메서드가 아니라 추가적인 메서드를 사용하는 경우 붙여야 한다
// 스프링부트 JPA가 트랜잭션까지 다 관리를 하는데 수동적인 쿼리를 수행해야 하는 경우 
// 영속성 컨텍스트 같은 부분을 데이터의 일관성을 유지하기 위해서 처리를 하는 부분
@Transactional
public class BoardServiceImpl implements BoardService {

	private final BoardRepository boardRepository;

	// 상품목록 호출
	public List<BoardDto> boardListFindAll() throws Exception {
		
		// findAll 호출해서 List 객체 Entity가 담겨서 온다
		// 레포지터리와 관련된 것은 무조건 엔티티 객체로 주고 받게 된다
		// 엔티티 리스트객체 상태
		// 엔티티 리스트객체를 디티오 리스트 객체로 줘야 한다 컨트롤러로
		// 변환하는 과정이 필요하다
		
		List<BoardEntity> boardEntityList = boardRepository.findAll();
		List<BoardDto> boardDtoList = new ArrayList<>();
		
		// 리스트에서 리스트로 옮겨 담아야 한다 그냥 대입은 불가능
		// 하나씩 꺼내서 옮겨담는 과정을 해줘야 한다
		
		for(BoardEntity boardEntity : boardEntityList) {
			boardDtoList.add(BoardDto.toBoardDto(boardEntity));
		}
		
		return boardDtoList;
	}
	
	// 상품등록
	public void writeBoardSave(BoardDto boardDto) throws Exception{
		BoardEntity boardEntity = BoardEntity.toWriteBoardSaveEntity(boardDto);
		boardRepository.save(boardEntity);
	}

	public void boardUpdateHits(Long boardNo) throws Exception {
		boardRepository.boardUpdateHits(boardNo);	
	}

	public BoardDto boardDetailFindById(Long boardNo) throws Exception {
		Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(boardNo);
		if(optionalBoardEntity.isPresent()) {
			BoardEntity boardEntity = optionalBoardEntity.get();
			BoardDto boardDto = BoardDto.toBoardDto(boardEntity);
			return boardDto;
		} else {	
			return null;
		}
	}
	
	
	
}

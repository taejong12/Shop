package com.shop.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shop.dto.BoardDto;
import com.shop.entity.BoardEntity;
import com.shop.entity.BoardFileEntity;
import com.shop.repository.BoardFileRepository;
import com.shop.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

import java.io.File;
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

	private final BoardFileRepository boardFileRepository;

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
		// 파일 첨부 여부에 따라 로직 분리
		if(boardDto.getBoardFile().isEmpty()) {
			// 첨부 파일 없음.
			BoardEntity boardEntity = BoardEntity.toWriteBoardSaveEntity(boardDto);
			boardRepository.save(boardEntity);
		} else {
			// 첨부 파일 있음.
			// 1. Dto에 담긴 파일을 꺼냄.
			// 2. 파일의 이름 가져옴
			// 3. 서버 저장용 이름을 만듬(하나 추가)
			// 내 사진.jpg => 48946954564_내사진.jpg
			// 4. 저장 경로 설정
			// 5. 해당 경로에 파일 저장
			// 6. board_table에 해당 데이터 save 처리
			// 7. board_file_table에 해당 데이터 save 처리

			MultipartFile boardFile = boardDto.getBoardFile();
			String originalFilename = boardFile.getOriginalFilename();
			// System.out.println("originalFilename : "+originalFilename);
			String storedFileName = System.currentTimeMillis() + "_" + originalFilename;
			// System.out.println("storedFileName : "+storedFileName);
			String savePath = "C:/springboot_shop_board_img/" + storedFileName; // 저장경로 반드시 있어야 한다. 저장까지 셋팅
			boardFile.transferTo(new File(savePath)); // 스프링멀티파트파일에서 제공하는 메서드, 지정한 경로로 파일을 넘긴다, 예외처리해줘야한다(서블릿쪽으로 떠넘기기) 
			// 여기까지 파일 저장만 처리

			// DB에 저장하는 내용
			BoardEntity boardEntity = BoardEntity.toSaveFileEntity(boardDto);

			// 저장되기 전이라 아직 boardNo 값이 없다
			Long savedId = boardRepository.save(boardEntity).getBoardNo(); // 부모자식 관계이기 때문에 board_no로 불러온다

			// DB에서 boardNo 값 가져옴
			BoardEntity board = boardRepository.findById(savedId).get(); // 부모 entity를 가져왔다, 전달이 돼야 한다

			// boardFileEntity로 변환해주는 작업
			BoardFileEntity boardFileEntity = BoardFileEntity.toBoardFileEntity(board, originalFilename, storedFileName);
			// System.out.println("boardFileEntity : "+boardFileEntity.getBoardFileStoredFileName());
			boardFileRepository.save(boardFileEntity); // DB에 저장까지만 한 내용
		}

	}

	// 상품 조회수 증가
	public void boardUpdateHits(Long boardNo) throws Exception {
		boardRepository.boardUpdateHits(boardNo);	
	}

	// 상품 상세보기
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

	// 상품 수정하기
	public BoardDto boardUpdate(BoardDto boardDto) throws Exception {
		
		BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDto);
		boardRepository.save(boardEntity);
		return boardDetailFindById(boardDto.getBoardNo());
	}

	// 상품 삭제하기
	public void boardDelete(Long boardNo) throws Exception {
		boardRepository.deleteById(boardNo);

	}

	// 상품 목록 페이징
	public Page<BoardDto> paging(Pageable pageable) throws Exception {
		int page = pageable.getPageNumber() - 1;
		int pageLimit =3; // 한 페이지에 보여줄 글 갯수
		// 한 페이지당 3개씩 글을 보여주고 정렬 기준은 memberNo 기준으로 내림차순 정렬
		// page 위치에 있는 값은 0부터 시작 그래서 -1빼고 시작함
		Page<BoardEntity> boardEntities = boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "boardNo")));

		System.out.println("boardEntities.getContent() =" + boardEntities.getContent()); // 요청 페이지에 해당하는 글
		System.out.println("boardEntities.getTotalElements() =" + boardEntities.getTotalElements()); // 전체 글갯수
		System.out.println("boardEntities.getNumber() =" + boardEntities.getNumber()); // DB로 요청한 페이지 번호
		System.out.println("boardEntities.getTotalPages() =" + boardEntities.getTotalPages()); // 전체 페이지 갯수
		System.out.println("boardEntities.getSize() =" + boardEntities.getSize()); // 한 페이지에 보여지는 글 갯수
		System.out.println("boardEntities.hasPrevious() =" + boardEntities.hasPrevious()); // 이전 페이지 존재 여부
		System.out.println("boardEntities.isFirst() =" + boardEntities.isFirst()); // 첫 페이지 여부
		System.out.println("boardEntities.isLast() =" + boardEntities.isLast()); // 마지막 페이지 여부

		// 페이지 객체 변환
		Page<BoardDto> boardDtos = boardEntities.map(board -> new BoardDto(board.getBoardNo(), board.getBoardTitle(), board.getBoardPrice(), board.getBoardStock(), board.getBoardHits(), board.getCreatedTime()));

		return boardDtos;
	}



}

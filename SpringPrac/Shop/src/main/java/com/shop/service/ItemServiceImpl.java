package com.shop.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shop.dto.ItemDto;
import com.shop.entity.ItemEntity;
import com.shop.entity.ItemFileEntity;
import com.shop.repository.ItemRepository;
import com.shop.repository.ItemFileRepository;

import lombok.RequiredArgsConstructor;

import java.io.File;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor

// JPA에서 제공하는 메서드가 아니라 추가적인 메서드를 사용하는 경우 붙여야 한다
// 스프링부트 JPA가 트랜잭션까지 다 관리를 하는데 수동적인 쿼리를 수행해야 하는 경우 
// 영속성 컨텍스트 같은 부분을 데이터의 일관성을 유지하기 위해서 처리를 하는 부분
@Transactional
public class ItemServiceImpl implements ItemService {

	private final ItemRepository itemRepository;

	private final ItemFileRepository itemFileRepository;

//	// 상품목록 호출
//	public List<itemDto> itemListFindAll() throws Exception {
//
//		// findAll 호출해서 List 객체 Entity가 담겨서 온다
//		// 레포지터리와 관련된 것은 무조건 엔티티 객체로 주고 받게 된다
//		// 엔티티 리스트객체 상태
//		// 엔티티 리스트객체를 디티오 리스트 객체로 줘야 한다 컨트롤러로
//		// 변환하는 과정이 필요하다
//
//		List<itemEntity> itemEntityList = itemRepository.findAll();
//		List<itemDto> itemDtoList = new ArrayList<>();
//
//		// 리스트에서 리스트로 옮겨 담아야 한다 그냥 대입은 불가능
//		// 하나씩 꺼내서 옮겨담는 과정을 해줘야 한다
//
//		for(itemEntity itemEntity : itemEntityList) {
//			itemDtoList.add(itemDto.toitemDto(itemEntity));
//		}
//
//		return itemDtoList;
//	}

	// 상품등록
	public void writeItemSave(ItemDto itemDto) throws Exception{
		// 파일 첨부 여부에 따라 로직 분리
		if(itemDto.getItemFile().isEmpty()) {
			// 첨부 파일 없음.
			ItemEntity itemEntity = ItemEntity.toWriteItemSaveEntity(itemDto);
			itemRepository.save(itemEntity);
		} else {
			// 첨부 파일 있음.
			// 1. Dto에 담긴 파일을 꺼냄.
			// 2. 파일의 이름 가져옴
			// 3. 서버 저장용 이름을 만듬(하나 추가)
			// 내 사진.jpg => 48946954564_내사진.jpg
			// 4. 저장 경로 설정
			// 5. 해당 경로에 파일 저장
			// 6. item_table에 해당 데이터 save 처리
			// 7. item_file_table에 해당 데이터 save 처리

			MultipartFile itemFile = itemDto.getItemFile();
			String originalFilename = itemFile.getOriginalFilename();
			// System.out.println("originalFilename : "+originalFilename);
			String storedFileName = System.currentTimeMillis() + "_" + originalFilename;
			// System.out.println("storedFileName : "+storedFileName);
			String savePath = "C:/springboot_shop_item_img/" + storedFileName; // 저장경로 반드시 있어야 한다. 저장까지 셋팅
			itemFile.transferTo(new File(savePath)); // 스프링멀티파트파일에서 제공하는 메서드, 지정한 경로로 파일을 넘긴다, 예외처리해줘야한다(서블릿쪽으로 떠넘기기) 
			// 여기까지 파일 저장만 처리

			// DB에 저장하는 내용
			ItemEntity itemEntity = ItemEntity.toSaveFileEntity(itemDto);

			// 저장되기 전이라 아직 itemNo 값이 없다
			Long savedId = itemRepository.save(itemEntity).getItemNo(); // 부모자식 관계이기 때문에 item_no로 불러온다

			// DB에서 itemNo 값 가져옴
			ItemEntity item = itemRepository.findById(savedId).get(); // 부모 entity를 가져왔다, 전달이 돼야 한다

			// itemFileEntity로 변환해주는 작업
			ItemFileEntity itemFileEntity = ItemFileEntity.toItemFileEntity(item, originalFilename, storedFileName);
			// System.out.println("itemFileEntity : "+itemFileEntity.getitemFileStoredFileName());
			itemFileRepository.save(itemFileEntity); // DB에 저장까지만 한 내용
		}

	}

	// 상품 조회수 증가
	public void itemUpdateHits(Long itemNo) throws Exception {
		itemRepository.itemUpdateHits(itemNo);	
	}

	// 상품 상세보기
	public ItemDto itemDetailFindById(Long itemNo) throws Exception {
		Optional<ItemEntity> optionalItemEntity = itemRepository.findById(itemNo);
		if(optionalItemEntity.isPresent()) {
			ItemEntity itemEntity = optionalItemEntity.get();
			//System.out.println("optionalitemEntity : "+ itemEntity.getitemFileEntityList().getitemFileNo());
			ItemDto itemDto = ItemDto.toItemDto(itemEntity);
			//System.out.println("itemDetailFindById : "+ itemDto.getitemFileNo());
			return itemDto;
		} else {	
			return null;
		}
	}

	// 상품 수정하기
	public ItemDto itemUpdate(ItemDto itemDto) throws Exception {
		ItemEntity itemEntity = ItemEntity.toUpdateEntity(itemDto);
		//System.out.println("itemUpdate ==:"+itemEntity);
		itemRepository.save(itemEntity);
		return itemDetailFindById(itemDto.getItemNo());
		
//		// 파일 첨부 여부에 따라 로직 분리
//		if(itemDto.getitemFile().isEmpty()) {
//			// 첨부 파일 없음.
//			itemEntity itemEntity = itemEntity.toUpdateEntity(itemDto);
//			System.out.println("itemUpdate ==:"+itemEntity);
//			itemRepository.save(itemEntity);
//			return itemDetailFindById(itemDto.getitemNo());
//		} else {
//			// 첨부 파일 있음.
//			// 1. Dto에 담긴 파일을 꺼냄.
//			// 2. 파일의 이름 가져옴
//			// 3. 서버 저장용 이름을 만듬(하나 추가)
//			// 내 사진.jpg => 48946954564_내사진.jpg
//			// 4. 저장 경로 설정
//			// 5. 해당 경로에 파일 저장
//			// 6. item_table에 해당 데이터 save 처리
//			// 7. item_file_table에 해당 데이터 save 처리
//			
			// 기존에 있던 파일을 삭제 후 업데이트 (디비에 저장된 이름과 폴더에 저장된 이름 비교 삭제?)
			
		
//			MultipartFile itemFile = itemDto.getitemFile();
//			String originalFilename = itemFile.getOriginalFilename();
//			System.out.println("originalFilename : "+originalFilename);
//			String storedFileName = System.currentTimeMillis() + "_" + originalFilename;
//			System.out.println("storedFileName : "+storedFileName);
//			String savePath = "C:/springboot_shop_item_img/" + storedFileName; // 저장경로 반드시 있어야 한다. 저장까지 셋팅
//			itemFile.transferTo(new File(savePath)); // 스프링멀티파트파일에서 제공하는 메서드, 지정한 경로로 파일을 넘긴다, 예외처리해줘야한다(서블릿쪽으로 떠넘기기) 
//			// 여기까지 파일 저장만 처리

			// 여기서부터 문제
			// SHOP_item 와 SHOP_item_FILE을 입력받은 값으로 UPDATE 해줘야 한다
			
			
//			// DB에 저장하는 내용
//			itemEntity itemEntity = itemEntity.toUpdateEntity(itemDto);
//			
//			// 저장되기 전이라 아직 itemNo 값이 없다
//			Long savedId = itemRepository.save(itemEntity).getitemNo(); // 부모자식 관계이기 때문에 item_no로 불러온다
//			System.out.println("savedId :"+savedId);
//			
//			// DB에서 itemNo 값 가져옴
//			itemEntity item = itemRepository.findById(savedId).get(); // 부모 entity를 가져왔다, 전달이 돼야 한다
//			System.out.println("item :"+item);
//			
////			itemFileEntity itemFile1 = itemFileRepository.findByitemNo(itemEntity.getitemNo());
////			System.out.println("itemFile ==:"+itemFile1);
//			
////			itemFileEntity itemFileEntity = itemFileEntity.toUpdateFileEntity(itemDto);
////			
////			itemFileEntity no = itemFileRepository.findByitemNo(); 
//			
//			Long itemFileNo = itemDto.getitemFileNo();
//			System.out.println("itemFileNo :"+itemFileNo);
//			
//			// itemFileEntity로 변환해주는 작업
//			itemFileEntity itemFileEntity = itemFileEntity.toitemFileUpdateEntity(originalFilename, storedFileName, item, itemFileNo);
//			System.out.println("itemFileEntity : "+itemFileEntity.getitemFileStoredFileName());
//			itemFileRepository.save(itemFileEntity); // DB에 저장까지만 한 내용
//			
//			System.out.println("getitemNo : " + itemDto.getitemNo());
//			return itemDetailFindById(itemDto.getitemNo());
//		}
	}

	// 상품 삭제하기
	public void itemDelete(Long itemNo) throws Exception {
		itemRepository.deleteById(itemNo);

	}

	// 상품 목록 페이징
	public Page<ItemDto> paging(Pageable pageable, String searchText) throws Exception {
		int page = pageable.getPageNumber() - 1;
		int pageLimit =3; // 한 페이지에 보여줄 글 갯수
		// 한 페이지당 3개씩 글을 보여주고 정렬 기준은 memberNo 기준으로 내림차순 정렬
		// page 위치에 있는 값은 0부터 시작 그래서 -1빼고 시작함
		Page<ItemEntity> itemEntities = itemRepository.findByItemTitleContainingOrItemContentContaining(searchText, searchText, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "itemNo")));

		System.out.println("itemEntities.getContent() =" + itemEntities.getContent()); // 요청 페이지에 해당하는 글
		System.out.println("itemEntities.getTotalElements() =" + itemEntities.getTotalElements()); // 전체 글갯수
		System.out.println("itemEntities.getNumber() =" + itemEntities.getNumber()); // DB로 요청한 페이지 번호
		System.out.println("itemEntities.getTotalPages() =" + itemEntities.getTotalPages()); // 전체 페이지 갯수
		System.out.println("itemEntities.getSize() =" + itemEntities.getSize()); // 한 페이지에 보여지는 글 갯수
		System.out.println("itemEntities.hasPrevious() =" + itemEntities.hasPrevious()); // 이전 페이지 존재 여부
		System.out.println("itemEntities.isFirst() =" + itemEntities.isFirst()); // 첫 페이지 여부
		System.out.println("itemEntities.isLast() =" + itemEntities.isLast()); // 마지막 페이지 여부

		// 페이지 객체 변환
		Page<ItemDto> itemDtos = itemEntities.map(item -> new ItemDto(item.getItemNo(), item.getItemTitle(), item.getItemPrice(), item.getItemStock(), item.getItemHits(), item.getCreatedTime(), item.getItemFileEntityList().getItemFileStoredFileName(), item.getItemFileAttached()));

		return itemDtos;
	}



}

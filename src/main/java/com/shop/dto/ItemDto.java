package com.shop.dto;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import com.shop.entity.ItemEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// DTO(Data Transfer Object), VO, Bean

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemDto {
	
	private Long itemNo; // 상품넘버
	private String itemTitle; //상품명
	private String itemContent; // 상품설명
	private int itemPrice; //상품가격
	private int itemStock; // 상품재고
	private int itemHits; // 상품조회수
	private Date itemCreateDate; // 상품등록일
	private Date itemUpdateDate; // 상품수정일
	
	private Long itemFileNo; // 파일넘버
	private MultipartFile itemFile; // jsp -> controller 파일 담는 용도
	private String originalItemFile; // 원본 파일 이름
	private String storedItemFile; // 서버 저장용 파일 이름
	private int itemFileAttached; // 이미지 첨부 여부(첨부 1, 미첨부 0)
	
	// 상품목록
	public static ItemDto toItemDto(ItemEntity itemEntity) {
		ItemDto itemDto = new ItemDto();
		//itemDto.setitemFileNo(itemEntity.getitemFileEntityList().getitemFileNo());
		itemDto.setItemNo(itemEntity.getItemNo());
		itemDto.setItemTitle(itemEntity.getItemTitle());
		itemDto.setItemContent(itemEntity.getItemContent());
		itemDto.setItemPrice(itemEntity.getItemPrice());
		itemDto.setItemStock(itemEntity.getItemStock());
		itemDto.setItemHits(itemEntity.getItemHits());
		itemDto.setItemCreateDate(itemEntity.getCreatedTime());
		itemDto.setItemUpdateDate(itemEntity.getUpdatedTime());
		if(itemEntity.getItemFileAttached()==0) { // 이미지 없으면 생략
			itemDto.setItemFileAttached(itemEntity.getItemFileAttached()); // 0
		} else {
			itemDto.setItemFileAttached(itemEntity.getItemFileAttached()); // 1
			// 파일 이름을 가져가야 함.
			// originalFileName, storedFileName : shop_item_file(itemFileEntity)
			// join
			// select * from shop_item b, shop_item_file bf where b.item_no = bf.item_no and b.id=?
			itemDto.setOriginalItemFile(itemEntity.getItemFileEntityList().getItemFileOriginalFileName());
			itemDto.setStoredItemFile(itemEntity.getItemFileEntityList().getItemFileStoredFileName());
		}
		
		return itemDto;
	}

	// 페이징 상품 목록
	public ItemDto(Long itemNo, String itemTitle, int itemPrice, int itemStock, int itemHits,
			Date itemCreateDate, String storedItemFile, int itemFileAttached) {
		this.itemNo = itemNo;
		this.itemTitle = itemTitle;
		this.itemPrice = itemPrice;
		this.itemStock = itemStock;
		this.itemHits = itemHits;
		this.itemCreateDate = itemCreateDate; 
		this.storedItemFile = storedItemFile;
		this.itemFileAttached=itemFileAttached;
	}
	
	
	
}
package com.shop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.dto.ItemDto;

public interface ItemService {

	// 상품목록조회
//	public List<itemDto> itemListFindAll() throws Exception;
	
	// 상품등록하기
	public void writeItemSave(ItemDto itemDto) throws Exception;
	
	// 상품조회수증가
	public void itemUpdateHits(Long itemNo) throws Exception;
	
	// 상품상세보기
	public ItemDto itemDetailFindById(Long itemNo) throws Exception;
	
	// 상품수정하기
	public ItemDto itemUpdate(ItemDto itemDto) throws Exception;
	
	// 상품삭제하기
	public void itemDelete(Long itemNo) throws Exception;
	
	// 상품목록페이징
	public Page<ItemDto> paging(Pageable pageable, String searchText) throws Exception;
}

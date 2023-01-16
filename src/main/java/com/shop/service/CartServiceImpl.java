package com.shop.service;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shop.dto.CartDto;
import com.shop.dto.ItemDto;
import com.shop.entity.CartEntity;
import com.shop.entity.ItemEntity;
import com.shop.repository.CartRepository;
import com.shop.repository.ItemFileRepository;
import com.shop.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

	private final CartRepository cartRepository;

	// 장바구니 추가
	@Override
	public void cartItemSave(CartDto cartDto) throws Exception {
		CartEntity cartEntity = CartEntity.toCartSaveEntity(cartDto);
		cartRepository.save(cartEntity);
		
	}

	// 장바구니 목록
//	@Override
//	public Page<CartDto> paging(Pageable pageable) throws Exception {
//		int page = pageable.getPageNumber() - 1;
//		int pageLimit =3; // 한 페이지에 보여줄 글 갯수
//		// 한 페이지당 3개씩 글을 보여주고 정렬 기준은 memberNo 기준으로 내림차순 정렬
//		// page 위치에 있는 값은 0부터 시작 그래서 -1빼고 시작함
//		Page<CartEntity> cartEntities = cartRepository.findByMemberNo(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "itemNo")));
//
//		// 페이지 객체 변환
////		Page<CartDto> cartDtos = cartEntities.map(cart -> new CartDto(cart.getCartNo(), cart.getMemberNo(), cart.getItemNo(), cart.get(), cart.itemTitle(), item.getCreatedTime(), item.getItemFileEntityList().getItemFileStoredFileName(), item.getItemFileAttached()));
//
//		return null;
//	}
	
	

	
}

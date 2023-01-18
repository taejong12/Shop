package com.shop.service;


import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shop.dto.CartDto;

import com.shop.entity.CartEntity;
import com.shop.entity.ItemEntity;
import com.shop.entity.MemberEntity;
import com.shop.repository.CartRepository;

import com.shop.repository.ItemRepository;
import com.shop.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

	private final CartRepository cartRepository;
	
	private final MemberRepository memberRepository;
	
	private final ItemRepository itemRepository;

	// 장바구니 추가
	@Override
	public CartDto cartItemSave(CartDto cartDto) throws Exception {
		
		Optional<MemberEntity> memberEntityOptional = memberRepository.findById(cartDto.getMemberNo());
		
		if(memberEntityOptional.isEmpty()) {
			return null;
		}
		
		MemberEntity memberEntity = memberEntityOptional.get();
		
		Optional<ItemEntity> itemEntityOptional = itemRepository.findById(cartDto.getItemNo());
		
		if(itemEntityOptional.isEmpty()) {
			return null;
		}
		
		ItemEntity itemEntity = itemEntityOptional.get();
		
		// Entity 저장
		CartEntity cartEntity = CartEntity.toCartSaveEntity(memberEntity, itemEntity, cartDto);
		
		// DB 저장하면서 저장된 cartNo 가져오기
		Long cartNo = cartRepository.save(cartEntity).getCartNo();
		
		Optional<CartEntity> cartEntityOptional = cartRepository.findById(cartNo);
		
		if(cartEntityOptional.isEmpty()) {
			return null;
		}
		
		CartEntity cartEntityData = cartEntityOptional.get();
		
		CartDto cartData = CartDto.toCartDto(cartEntityData);
		
		
		return cartData;
	}

	// 장바구니 중복체크
	@Override
	public CartDto cartItemCheck(CartDto cartDto) throws Exception {
		
		// 해당하는 멤버 조회
		Optional<MemberEntity> memberEntity = memberRepository.findById(cartDto.getMemberNo());
		
		// 해당하는 상품 조회
		Optional<ItemEntity> itemEntity = itemRepository.findById(cartDto.getItemNo());
		
		// 해당하는 멤버와 상품의 장바구니 조회
		CartEntity cartEntity = cartRepository.findByMemberEntityAndItemEntity(memberEntity.get(), itemEntity.get());
		CartDto cart = CartDto.toCartCheck(cartEntity);
		return cart;
	}

	// 장바구니 목록
	@Override
	public Page<CartDto> cartPaging(Pageable pageable, Long memberNo) throws Exception {
		int page = pageable.getPageNumber() - 1;
		int pageLimit =3; // 한 페이지에 보여줄 글 갯수
		// 한 페이지당 3개씩 글을 보여주고 정렬 기준은 memberNo 기준으로 내림차순 정렬
		// page 위치에 있는 값은 0부터 시작 그래서 -1빼고 시작함
		
		// 해당하는 멤버 조회
		Optional<MemberEntity> memberEntity = memberRepository.findById(memberNo);
				
		// 멤버 아이디 해당하는 페이지
		Page<CartEntity> cartEntities = cartRepository.findByMemberEntity(memberEntity.get(), PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "itemEntity")));
		
		// 페이지 객체 변환
		Page<CartDto> cartPage = cartEntities.map(cart -> new CartDto(cart.getCartNo(), cart.getMemberEntity().getMemberNo(), cart.getItemEntity().getItemNo(), cart.getItemEntity().getItemFileEntityList().getItemFileStoredFileName(), cart.getItemEntity().getItemTitle(), cart.getItemEntity().getItemPrice(), cart.getCartItemAmount(), cart.getCreatedTime(), cart.getUpdatedTime()));
		
		return cartPage;
	}

}

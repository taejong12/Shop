package com.shop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.shop.dto.OrderDto;
import com.shop.dto.OrderListDto;
import com.shop.entity.ItemEntity;
import com.shop.entity.MemberEntity;
import com.shop.entity.OrderEntity;
import com.shop.repository.ItemRepository;
import com.shop.repository.MemberRepository;
import com.shop.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	
	private final ItemRepository itemRepository;
	
	private final MemberRepository memberRepository;

	// 결제 페이지 장바구니 상품 목록
	@Override
	public List<OrderDto> cartItemOrderList(List<OrderDto> orders) throws Exception {
		
		List<OrderDto> orderList = new ArrayList<>();

		for(OrderDto ord : orders) {
//			System.out.println("ord : "+ord);
			
			// 상품 정보 조회
			Optional<ItemEntity> itemInfo = itemRepository.findById(ord.getItemNo());
			ItemEntity itemEntity = itemInfo.get();
			
			// 주문 Dto에 상품 정보 저장
			OrderDto orderDto = OrderDto.toOrderDto(itemEntity);
			
			// 받아온 상품 갯수와 가격 Dto에 저장
			orderDto.setCartItemAmount(ord.getCartItemAmount());
			
			// 상품 가격 + 총 갯수
			orderDto.initItemTotalPrice();
			orderList.add(orderDto);
		}
		 
		return orderList;
		
		
	}

	@Override
	public OrderDto memberInfo(Long memberNo) throws Exception {
		
		Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(memberNo);
		
		if(optionalMemberEntity.isPresent()) {
			MemberEntity memberEntity = optionalMemberEntity.get();
			OrderDto orderDto = OrderDto.toOrderDtoMember(memberEntity);
			return orderDto;
		}
		
		return null;
	}

	// 결제완료
	@Override
	public void orderSave(Long memberNo, List<OrderDto> orders) throws Exception {
		
		System.out.println("orders : " +orders);
		
		Optional<MemberEntity> memberEntityOptional = memberRepository.findById(memberNo);
		
		MemberEntity memberEntity = memberEntityOptional.get();
		
		System.out.println("memberEntity : "+memberEntity.getMemberNo());

		for(OrderDto ord : orders) {
			System.out.println("ord : "+ord);
			
			// 상품 정보 조회
			Optional<ItemEntity> itemInfo = itemRepository.findById(ord.getItemNo());
			ItemEntity itemEntity = itemInfo.get();
			
			// 주문 Dto에 상품 정보 저장
			OrderEntity orderEntity = OrderEntity.toOrderSave(memberEntity, ord, itemEntity);
			
			System.out.println("orderEntity : "+ orderEntity);
			orderRepository.save(orderEntity);
			
		}
		
	}
	
}

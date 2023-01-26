package com.shop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shop.dto.CartDto;
import com.shop.dto.OrderDto;
import com.shop.dto.OrderListDto;
import com.shop.entity.CartEntity;
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

	// 결제페이지 회원정보
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

	// 결제완료(장바구니)
	@Override
	public void orderSave(Long memberNo, List<OrderDto> orders) throws Exception {
		
		Optional<MemberEntity> memberEntityOptional = memberRepository.findById(memberNo);
		
		if(memberEntityOptional.isPresent()) {
			MemberEntity memberEntity = memberEntityOptional.get();	

			for(OrderDto ord : orders) {
				
				// 상품 정보 조회
				Optional<ItemEntity> itemInfo = itemRepository.findById(ord.getItemNo());
				ItemEntity itemEntity = itemInfo.get();
				
				// 주문 Dto에 상품 정보 저장
				OrderEntity orderEntity = OrderEntity.toOrderSave(memberEntity, ord, itemEntity);
					
				orderRepository.save(orderEntity);
				
			}
		}
		
	}

	// 주문내역 목록
	@Override
	public Page<OrderDto> orderPaging(Pageable pageable, Long memberNo) throws Exception {
		int page = pageable.getPageNumber() - 1;
		int pageLimit =3;
		
		// 해당하는 멤버 조회
		Optional<MemberEntity> memberEntity = memberRepository.findById(memberNo);
				
		// 멤버 아이디 해당하는 페이지
		Page<OrderEntity> orderEntities = orderRepository.findByMemberEntity(memberEntity.get(), PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "orderNo")));
		
		// 페이지 객체 변환
		Page<OrderDto> orderPage = orderEntities.map(order -> new OrderDto(order.getOrderNo(), order.getMemberEntity().getMemberNo(), order.getItemEntity().getItemNo(), order.getItemEntity().getItemFileEntityList().getItemFileStoredFileName(), order.getItemEntity().getItemTitle(), order.getItemEntity().getItemPrice(), order.getItemCount(), order.getItemTotalPrice(), order.getMemberEntity().getMemberId() , order.getCreatedTime(), order.getUpdatedTime(), order.getItemPayStatus()));
				
		return orderPage;
	}

	// 결제취소
	@Override
	public void orderCancel(OrderDto orderDto) throws Exception {
		Optional<MemberEntity> memberEntity = memberRepository.findById(orderDto.getMemberNo());
		Optional<ItemEntity> itemEntity = itemRepository.findById(orderDto.getItemNo());
		
		OrderEntity orderEntity = OrderEntity.toOrderCancel(orderDto, itemEntity.get(), memberEntity.get());
		orderRepository.save(orderEntity);
		
	}
	
}

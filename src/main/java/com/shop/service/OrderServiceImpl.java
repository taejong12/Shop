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
import com.shop.dto.ItemDto;
import com.shop.dto.OrderDto;
import com.shop.dto.OrderListDto;
import com.shop.entity.CartEntity;
import com.shop.entity.ItemEntity;
import com.shop.entity.MemberEntity;
import com.shop.entity.OrderEntity;
import com.shop.repository.CartRepository;
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
	
	private final CartRepository cartRepository;

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
			OrderDto orderDto = OrderDto.toOrderDto(itemEntity, ord);
			
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
		
		System.out.println("orders : "+orders);
		
		Optional<MemberEntity> memberEntityOptional = memberRepository.findById(memberNo);
		
		if(memberEntityOptional.isPresent()) {
			MemberEntity memberEntity = memberEntityOptional.get();	

			for(OrderDto ord : orders) {
				
				System.out.println("ord : " +ord);
				
				// 상품 정보 조회
				Optional<ItemEntity> itemInfo = itemRepository.findById(ord.getItemNo());
				ItemEntity itemEntity = itemInfo.get();
				
				// 구매한 수량만큼 재고 차감
				ItemDto itemDto = ItemDto.toOrderItem(itemEntity, ord);
				System.out.println("itemDto : " + itemDto);
				
				// 재고 차감된 상품 업데이트
				ItemEntity itemUpdateEntity = ItemEntity.toOrderUpdate(itemDto);
				itemRepository.save(itemUpdateEntity);

				System.out.println("ord.getCartNo() : "+ord.getCartNo());
				
				// 구매한 상품의 장바구니 삭제
				cartRepository.deleteById(ord.getCartNo());
				
				// 주문 상품 정보 저장
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
		
		// 결제 취소한 수량만큼 재고 증가
		ItemDto itemDto = ItemDto.toOrderCancel(itemEntity.get(), orderDto);
		System.out.println("itemDto : " + itemDto);
		
		// 재고 증가된 상품 업데이트
		ItemEntity itemUpdateEntity = ItemEntity.toOrderUpdate(itemDto);
		itemRepository.save(itemUpdateEntity);

		// 결제 취소 저장
		OrderEntity orderEntity = OrderEntity.toOrderCancel(orderDto, itemEntity.get(), memberEntity.get());
		orderRepository.save(orderEntity);
		
	}
	
}

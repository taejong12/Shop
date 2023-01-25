package com.shop.dto;

import java.sql.Date;

import com.shop.entity.ItemEntity;
import com.shop.entity.MemberEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class OrderDto {

	private Long orderNo; // 주문 넘버
	private Long itemNo; // 상품 넘버
	private Long memberNo; // 회원 넘버
	
	// 상품 테이블
	private String itemTitle; // 상품명
	private int itemPrice; // 상품가격
	private String storedItemFile; // 상품 이미지 이름
	
	// 멤버 테이블
	private String memberId; // 회원 아이디
	private String memberName; // 멤버 이름
	private String memberEmail; // 멤버 이메일
	
	private int cartItemAmount; // 상품 총 갯수
	private int itemTotalPrice; // 상품 총 가격
	
	private String itemPayStatus; // 결제상태
	private Date orderItemCreateDate; // 상품결제일
	private Date orderItemUpdateDate; // 상품결제수정일
	
	public void initItemTotalPrice() {
		this.itemTotalPrice = this.itemPrice * this.cartItemAmount;
	}

	// 상품 정보 저장
	public static OrderDto toOrderDto(ItemEntity itemEntity) {
		OrderDto orderDto = new OrderDto();
		orderDto.setItemTitle(itemEntity.getItemTitle());
		orderDto.setItemPrice(itemEntity.getItemPrice());
		orderDto.setStoredItemFile(itemEntity.getItemFileEntityList().getItemFileStoredFileName());
		return orderDto;
	}

	// 회원 정보 저장
	public static OrderDto toOrderDtoMember(MemberEntity memberEntity) {
		OrderDto orderDto = new OrderDto();
		orderDto.setMemberId(memberEntity.getMemberId());
		orderDto.setMemberName(memberEntity.getMemberName());
		orderDto.setMemberEmail(memberEntity.getMemberEmail());
		return orderDto;
	}
	
}

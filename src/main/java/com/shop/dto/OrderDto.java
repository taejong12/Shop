package com.shop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class OrderDto {

	private Long orderNo; // 주문넘버
	private Long itemNo; // 상품넘버
	private Long memberNo; // 회원넘버
	private String memberId; // 회원 아이디
	private String itemTitle; // 상품명
	private int itemPrice; // 상품가격
	private String originalItemFile; // 상품 이미지 이름
	
	private int itemTotalCount; // 총 갯수
	private int itemTotalPrice;	// 총 가격
	
}

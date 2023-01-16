package com.shop.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CartDto {

	private Long cartNo; // 장바구니 넘버
	private Long memberNo; // 멤버 넘버
	private Long itemNo; // 상품 넘버
	private String storedItemFile; // 서버 저장용 파일 이름 (상품 이미지)
	private String itemTitle; //상품명
	private int itemPrice; //상품가격
	private int cartItemAmount; // 상품 총 갯수
	private Date cartItemCreateDate; // 장바구니 상품등록일
	private Date cartItemUpdateDate; // 장바구니 상품수정일
	
	// 페이징 장바구니 목록
	public CartDto(Long cartNo, Long memberNo, Long itemNo, String storedItemFile, String itemTitle, int itemPrice,
			int cartItemAmount, Date cartItemCreateDate, Date cartItemUpdateDate) {
		super();
		this.cartNo = cartNo;
		this.memberNo = memberNo;
		this.itemNo = itemNo;
		this.storedItemFile = storedItemFile;
		this.itemTitle = itemTitle;
		this.itemPrice = itemPrice;
		this.cartItemAmount = cartItemAmount;
		this.cartItemCreateDate = cartItemCreateDate;
		this.cartItemUpdateDate = cartItemUpdateDate;
	}
	
	
	
}

package com.shop.dto;

import java.sql.Date;

import com.shop.entity.CartEntity;

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
	private int itemStock; // 상품 재고
	private Date cartItemCreateDate; // 장바구니 상품등록일
	private Date cartItemUpdateDate; // 장바구니 상품수정일
	
	public static CartDto toCartDto(CartEntity cartEntity) {
		CartDto cartDto = new CartDto();
		// Entity를 Dto로 변환하는 작업
		cartDto.setCartNo(cartEntity.getCartNo());
		cartDto.setMemberNo(cartEntity.getMemberEntity().getMemberNo());
		cartDto.setItemNo(cartEntity.getItemEntity().getItemNo());
		
		if(cartEntity.getItemEntity().getItemFileAttached()==1) {
			cartDto.setStoredItemFile(cartEntity.getItemEntity().getItemFileEntityList().getItemFileStoredFileName());
		}
		
		cartDto.setItemTitle(cartEntity.getItemEntity().getItemTitle());
		cartDto.setItemPrice(cartEntity.getItemEntity().getItemPrice());
		cartDto.setCartItemAmount(cartEntity.getCartItemAmount());
		cartDto.setCartItemCreateDate(cartEntity.getCreatedTime());
		cartDto.setCartItemUpdateDate(cartEntity.getUpdatedTime());

		return cartDto;
	}
	
	// 페이징 장바구니 목록
	public CartDto(Long cartNo, Long memberNo, Long itemNo, String storedItemFile, String itemTitle, int itemPrice,
			int cartItemAmount, Date cartItemCreateDate, Date cartItemUpdateDate, int itemStock) {
		this.cartNo = cartNo;
		this.memberNo = memberNo;
		this.itemNo = itemNo;
		this.storedItemFile = storedItemFile;
		this.itemTitle = itemTitle;
		this.itemPrice = itemPrice;
		this.cartItemAmount = cartItemAmount;
		this.cartItemCreateDate = cartItemCreateDate;
		this.cartItemUpdateDate = cartItemUpdateDate;
		this.itemStock = itemStock;
	}

	// 장바구니 확인
	public static CartDto toCartCheck(CartEntity cartEntity) {
		CartDto cartDto = new CartDto();
		
		if(cartEntity != null) {
			cartDto.setMemberNo(cartEntity.getMemberEntity().getMemberNo());
			cartDto.setItemNo(cartEntity.getItemEntity().getItemNo());
		}
		
		return cartDto;
	}
	
	
	
}

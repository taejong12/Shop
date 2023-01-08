package com.shop.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderItemDTO {
	
	private String orNum; // 주문번호
	private int orTotalAmount; // 총수량
	private String orPayStatus; // 결제상태
	private int orTotalPrice; // 총가격
	private Date orPayDate; // 결제일자
	private String orPostNum ; // 우편번호
	private String orRoadAddress; // 도로명주소
	private String orDetailAddress; // 상세주소
	private String userName; // 로그인 아이디
	private int alNum; // 주류번호
	private int caCount; // 개별 주류 수량 **
	private int alPrice; // 개별 주류 가격 **
	private String alOpic;//사진
	private String alName;//주류이름
	private String payMethod; // 결제방법
	private String phone; // 고객핸드폰번호
	private String email; // 고객이메일
	
	
	public void initSaleTotal() {
		
		this.orTotalPrice = this.alPrice*this.caCount;
		
	}
	
}


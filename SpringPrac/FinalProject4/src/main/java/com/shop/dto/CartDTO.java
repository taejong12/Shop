package com.shop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartDTO {
	private int caNum;
    
    private String id;
    
    private int alNum;
    
    private int caCount;
    
   
    //술 정보
    private String alName;
    
    private int alPrice;
    
    private String alOpic;
    
    
    // 추가 
    private int totalPrice;
}

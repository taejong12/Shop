package com.shop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CateFilterDTO {
	/* 카테고리 이름 */
	private String cateName;
	
	/* 카테고리 넘버 */
	private String cateCode;;
	
	/* 카테고리 상품 수 */
	private int cateCount;	
	
	/* 국내,국외 분류 */
	private String cateGroup;
}

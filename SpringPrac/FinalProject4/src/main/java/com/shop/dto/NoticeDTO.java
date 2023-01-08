package com.shop.dto;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NoticeDTO {
	private String id;
	private String title;
	private String content;
	private String writer;
	private String regdate;	
	private int visitCount;
	
}

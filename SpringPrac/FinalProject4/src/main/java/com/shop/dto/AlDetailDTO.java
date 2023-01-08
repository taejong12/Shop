package com.shop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AlDetailDTO {
	private int kiNum;
	private String kiName;
	private int alNum;
	private String alOpic;
	private String alName;
	private String alAbv;
	private String alVolume;
	private int alPrice;
	private String alJaeryo;
	private int alStock;
	private int maNum;
	
	private String flDanmat;//단맛
	private String flSinmat;
	private String flSsunmat;
	private String flBody;//농도
	
	private String flTansan;
	
	private String maName;
	private String maAddr;
	private double maLati;
	private double maLong;
	private String deOpic1;
	private String deOpic2;
	private String deOpic3;
	

	
	
}

package com.shop.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class OrderListDto {
	private List<OrderDto> orders;

}

package com.shop.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@ToString
@Setter
@Getter
public class OrderListDTO {
	private List<OrderItemDTO> orders;
}

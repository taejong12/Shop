package com.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.shop.dto.OrderItemDTO;

@Mapper
public interface OrderDAO {
	public OrderItemDTO getGoodsInfo(int alNum); //**
	public List<OrderItemDTO> getOrderList(String username);
	public int orderPageDao(Map<String,String> map);
	public int orderInsertDao(Map<String, String> map);
	public OrderItemDTO orderPage(String username);
}

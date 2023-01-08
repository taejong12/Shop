package com.shop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.dao.OrderDAO;
import com.shop.dto.CartDTO;
import com.shop.dto.OrderItemDTO;


@Service
public class OrderServiceImp implements OrderService {
	
	@Autowired
	private OrderDAO dao;

	@Override
	public List<OrderItemDTO> getOrderList(String username) {
		
		return dao.getOrderList(username);
	}
	
	public int orderPage(Map<String,String> map) {
		return dao.orderPageDao(map);
	}

	@Override
	public int orderInsert(Map<String, String> map) {
		return dao.orderInsertDao(map);
	}

	@Override
	public OrderItemDTO orderPage(String username) {
	
		return dao.orderPage(username);
	}

	@Override
	public List<OrderItemDTO> getGoodsInfo(List<OrderItemDTO> orders) {
		List<OrderItemDTO> result = new ArrayList<OrderItemDTO>();		
		
		for(OrderItemDTO ord : orders) {
			
			OrderItemDTO goodsInfo = dao.getGoodsInfo(ord.getAlNum());			
			System.out.println(ord.getAlNum());
			goodsInfo.setCaCount(ord.getCaCount());			
			goodsInfo.initSaleTotal();
			result.add(goodsInfo);			
		}		
		
		return result;
	};
	

}



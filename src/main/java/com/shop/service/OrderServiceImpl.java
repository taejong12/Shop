package com.shop.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.shop.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	
}

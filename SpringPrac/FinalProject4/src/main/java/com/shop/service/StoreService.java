package com.shop.service;

import java.util.List;

import com.shop.dto.StoreDTO;

public interface StoreService {
	
	public List<StoreDTO> list(String alKind);
}

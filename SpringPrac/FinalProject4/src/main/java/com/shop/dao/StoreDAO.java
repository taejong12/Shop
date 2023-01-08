package com.shop.dao;

import java.util.List;

import com.shop.dto.StoreDTO;


public interface StoreDAO {
	public List<StoreDTO> listDAO(String alKind);
}

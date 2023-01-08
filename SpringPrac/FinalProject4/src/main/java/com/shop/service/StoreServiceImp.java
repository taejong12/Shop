package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.dao.StoreDAO;
import com.shop.dto.StoreDTO;

@Service
public class StoreServiceImp implements StoreService{
	
	@Autowired
	StoreDAO dao;
	
	@Override
	public List<StoreDTO> list(String alKind) {
         return dao.listDAO(alKind);
    }

}

package com.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.dao.AlDetailDAO;
import com.shop.dto.AlDetailDTO;
import com.shop.dto.detailFoodDTO;

@Service
public class AlDetailServiceImp implements AlDetailService{
	
	@Autowired
	AlDetailDAO dao;

	@Override
	public AlDetailDTO detailView(int alNum) {	
		return dao.detailViewDAO(alNum);
	}

	@Override
	public detailFoodDTO detailFood() {
		return dao.detailFoodDAO();
	}

}

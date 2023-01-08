package com.shop.dao;

import org.apache.ibatis.annotations.Mapper;

import com.shop.dto.AlDetailDTO;
import com.shop.dto.detailFoodDTO;

@Mapper
public interface AlDetailDAO {
	public AlDetailDTO detailViewDAO(int alNum);
	public detailFoodDTO detailFoodDAO();
}

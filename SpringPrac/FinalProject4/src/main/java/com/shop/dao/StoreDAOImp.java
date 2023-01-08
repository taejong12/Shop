package com.shop.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shop.dto.StoreDTO;

@Repository
public class StoreDAOImp implements StoreDAO{
	@Autowired
	SqlSession sql;

	@Override
	public List<StoreDTO> listDAO(String alkind) {
		
		return sql.selectList("StoreDAO.listDAO", alkind);
	}
}

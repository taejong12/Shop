package com.shop.dao;

import java.util.Map;

import com.shop.dto.JoinDTO;

public interface UserDAO {
	void join(JoinDTO  join);
	int overlapCheck(String value, String valueType);
	void modifyInfo(Map<String, Object> map);
}



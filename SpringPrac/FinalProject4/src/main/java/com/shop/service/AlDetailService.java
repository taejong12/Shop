package com.shop.service;

import com.shop.dto.AlDetailDTO;
import com.shop.dto.UserDTO;
import com.shop.dto.detailFoodDTO;

public interface AlDetailService {
	AlDetailDTO detailView(int alNum); 
	detailFoodDTO detailFood();
	
}

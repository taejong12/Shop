package com.shop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.dao.NoticeDAO;
import com.shop.dto.NoticeDTO;
import com.shop.pagemodel.Criteria;

@Service
public class NoticeServiceImp implements NoticeService{

	@Autowired
	NoticeDAO dao;

	@Override
	public List<NoticeDTO> noticeListDao() {
	
		return dao.noticeListDao();
	}


	@Override
	public NoticeDTO noticeViewDao(String id) {
		
		return dao.noticeViewDao(id);
	}

	@Override
	public int noticeWriteDao(Map<String, String> map) {
		
		return dao.noticeWriteDao(map);
	}

	@Override
	public int noticeUpdateDao(Map<String, String> map) {
		
		return dao.noticeUpdateDao(map);
	}

	@Override
	public int noticeDeleteDao(String id) {
		
		return dao.noticeDeleteDao(id);
	}

	@Override
	public int noticeVisitCount(String id) {
		
		return dao.noticeVisitCount(id);
	}

	@Override
	public int noticePostCount() {
		
		return dao.noticePostCount();
	}
	
	@Override
	public List<NoticeDTO> noticeGetListPaging(Criteria cri) {
		
		return dao.noticeGetListPaging(cri);
	}
	
}

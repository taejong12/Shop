package com.shop.service;

import java.util.List;
import java.util.Map;

import com.shop.dto.NoticeDTO;
import com.shop.pagemodel.Criteria;


public interface NoticeService {
	public List<NoticeDTO> noticeListDao();
	public NoticeDTO noticeViewDao(String id);
	public int noticeWriteDao(Map<String,String> map);
	public int noticeUpdateDao(Map<String,String> map);
	public int noticeDeleteDao(String id);
	public int noticeVisitCount(String id);
	public int noticePostCount();
	public List<NoticeDTO> noticeGetListPaging(Criteria cri);
}

package com.shop.dao;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shop.dto.CateFilterDTO;
import com.shop.dto.FilterCriteriaDTO;
import com.shop.dto.FilterSearchDTO;


@Mapper
public interface FilterSearchDAO {

    /* 상품 리스트 */
	public List<FilterSearchDTO> getListPaging(FilterCriteriaDTO cri);
	
	/* 게시판 총 갯수 */
    public int getTotal(FilterCriteriaDTO cri);
    
    /* 검색 대상 카테고리 리스트 */
	public String[] getCateList(FilterCriteriaDTO cri);
	
	/* 카테고리 정보(+검색대상 갯수) */
	public CateFilterDTO getCateInfo(FilterCriteriaDTO cri);
	
}



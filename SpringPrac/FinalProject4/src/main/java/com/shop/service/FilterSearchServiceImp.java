package com.shop.service;


import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.dao.FilterSearchDAO;
import com.shop.dto.CateFilterDTO;
import com.shop.dto.FilterCriteriaDTO;
import com.shop.dto.FilterSearchDTO;



@Service
public class FilterSearchServiceImp implements FilterSearchService{

	
	@Autowired
	FilterSearchDAO dao;

	@Override
	public List<FilterSearchDTO> getListPaging(FilterCriteriaDTO cri) {
	
		return dao.getListPaging(cri);
	}

	@Override
	public int getTotal(FilterCriteriaDTO cri) {
		
		return dao.getTotal(cri);
	}

	
	@Override
	public List<CateFilterDTO> getCateInfoList(FilterCriteriaDTO cri) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public List<CateFilterDTO> getCateInfoList(FilterCriteriaDTO cri) {
//		
//		List<CateFilterDTO> filterInfoList = new ArrayList<CateFilterDTO>();
//		
//		String[] typeArr = cri.getType().split("");
//		String [] authorArr;
//		
//		for(String type : typeArr) {
//			if(type.equals("A")){
//				authorArr = dao.getAuthorIdList(cri.getKeyword());
//				if(authorArr.length == 0) {
//					return filterInfoList;
//				}
//				cri.setAuthorArr(authorArr);
//			}
//		}
//		
//		String[] cateList = dao.getCateList(cri);
//		
//		String tempCateCode = cri.getCateCode();
//		
//		for(String cateCode : cateList) {
//			cri.setCateCode(cateCode);
//			CateFilterDTO filterInfo = dao.getCateInfo(cri);
//			filterInfoList.add(filterInfo);
//		}
//		
//		cri.setCateCode(tempCateCode);
//		
//		return filterInfoList;
//	}
//

	



}

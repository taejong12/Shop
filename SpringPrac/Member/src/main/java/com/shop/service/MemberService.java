package com.shop.service;

import com.shop.dto.MemberDto;

public interface MemberService {
	
	public void save(MemberDto memberDto) throws Exception;
	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto memberInformationFindById(Long memberNo) throws Exception;
	public MemberDto memberUpdateFormFindById(Long memberNo) throws Exception;
	public void memberUpdate(MemberDto memberDto) throws Exception;
	public void memberDelete(Long memberNo) throws Exception;
	public MemberDto idCheck(String memberId) throws Exception;
	public MemberDto emailCheck(String memberEmail) throws Exception;
}

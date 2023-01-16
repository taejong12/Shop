package com.shop.service;


import com.shop.dto.MemberDto;

public interface MemberService {
	
	// 회원가입
	public void save(MemberDto memberDto) throws Exception;
	
	// 로그인
	public MemberDto login(MemberDto memberDto) throws Exception;
	
	// 회원정보페이지
	public MemberDto memberInformationFindById(Long memberNo) throws Exception;
	
	// 회원수정페이지
	public MemberDto memberUpdateFormFindById(Long memberNo) throws Exception;
	
	// 회원수정하기
	public void memberUpdate(MemberDto memberDto) throws Exception;
	
	// 회원탈퇴하기
	public void memberDelete(Long memberNo) throws Exception;
	
	// 회원가입 아이디 중복확인
	public MemberDto idCheck(String memberId) throws Exception;
	
	// 회원가입 이메일 중복확인
	public MemberDto emailCheck(String memberEmail) throws Exception;
}
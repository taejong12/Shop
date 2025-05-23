package com.shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

	// 아이디로 회원 정보 조회 (select * from shop_member where member_id=?)
	// 자바 유틸에서 제공하는 클래스로 널 방지를 해주는 내용이다. 보통 파인드바이아이디 회원상세조회
	// 기본적으로 옵션널 객체로 감싸서 객체를 넘겨준다 형식에 맞게 메서드를 정의했다.
	
	// 멤버 고유넘버
	Optional<MemberEntity> findByMemberNo(Long memberNo);
	
	// 멤버 로그인 아이디
	Optional<MemberEntity> findByMemberId(String memberId);

	// 멤버 이메일
	Optional<MemberEntity> findByMemberEmail(String memberEmail);

}
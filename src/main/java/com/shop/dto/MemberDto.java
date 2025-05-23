package com.shop.dto;

import java.sql.Date;

import com.shop.entity.MemberEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

// 기본 생성자를 자동으로 만들어준다
@NoArgsConstructor
public class MemberDto {

	private Long memberNo; // 멤버 넘버
	private String memberId; // 멤버 아이디
	private String memberPassword; // 멤버 비밀번호
	private String memberName; // 멤버 이름
	private String memberEmail; // 멤버 이메일
	private Date memberCreateDate; // 멤버 생성일
	private Date memberUpdateDate; // 멤버 수정일
	private String memberRole; // 멤버 역할
	
	// Entity에 작성했던 내용을 반대로 작성하면 된다.
	// Entity 객체를 Dto 객체로 변환하는 법
	public static MemberDto toMemberDto(MemberEntity memberEntity) {
		
		MemberDto memberDto = new MemberDto();
		
		// Entity를 Dto로 변환하는 작업
		memberDto.setMemberNo(memberEntity.getMemberNo());
		memberDto.setMemberId(memberEntity.getMemberId());
		memberDto.setMemberPassword(memberEntity.getMemberPassword());
		memberDto.setMemberName(memberEntity.getMemberName());
		memberDto.setMemberEmail(memberEntity.getMemberEmail());
		memberDto.setMemberRole(memberEntity.getMemberRole());
		memberDto.setMemberCreateDate(memberEntity.getCreatedTime());
		memberDto.setMemberUpdateDate(memberEntity.getUpdatedTime());
		
		
		return memberDto;
	}
}
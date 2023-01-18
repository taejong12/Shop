package com.shop.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.shop.dto.MemberDto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="shop_member")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator="SHOP_MEMBER_SEQ_GEN" )
	@SequenceGenerator(
            name="SHOP_MEMBER_SEQ_GEN", //시퀀스 제너레이터 이름
            sequenceName="SHOP_MEMBER_SEQ", //시퀀스 이름
            initialValue=1, //시작값
            allocationSize=1 //메모리를 통해 할당할 범위 사이즈
            )
	private Long memberNo;
	
	// 회원아이디
	@Column(nullable=false)
	private String memberId;
	
	// 회원비밀번호
	@Column(nullable=false)
	private String memberPassword;
	
	// 회원이름
	@Column(nullable=false)
	private String memberName;
	
	// 회원이메일
	@Column(nullable=false)
	private String memberEmail;
	
	// 회원
	@Column(nullable=false)
	private String memberRole;
	
	// 장바구니 조인
	@OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<CartEntity> cartEntityList = new ArrayList<>();
	
	// 회원가입 insert
	public static MemberEntity toMemberEntity(MemberDto memberDto) {
		MemberEntity memberEntity = new MemberEntity();
		
		// Dto에 담긴 값을 Entity로 옮기는 작업
		memberEntity.setMemberId(memberDto.getMemberId());
		memberEntity.setMemberPassword(memberDto.getMemberPassword());
		memberEntity.setMemberEmail(memberDto.getMemberEmail());
		memberEntity.setMemberName(memberDto.getMemberName());
		memberEntity.setMemberRole(memberDto.getMemberRole());
		
		return memberEntity;
		
	}
	
	// 회원정보수정 update
	public static MemberEntity toMemberUpdateEntity(MemberDto memberDto) {
		MemberEntity memberEntity = new MemberEntity();
		
		// Dto에 담긴 값을 Entity로 옮기는 작업
		memberEntity.setMemberNo(memberDto.getMemberNo());
		memberEntity.setMemberId(memberDto.getMemberId());
		memberEntity.setMemberPassword(memberDto.getMemberPassword());
		memberEntity.setMemberEmail(memberDto.getMemberEmail());
		memberEntity.setMemberName(memberDto.getMemberName());
		memberEntity.setMemberRole(memberDto.getMemberRole());
		
		return memberEntity;
		
	}
	
}
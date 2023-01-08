package com.test.crud.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.relational.core.mapping.Column;

import com.test.crud.role.MemberRole;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="member")
public class MemberEntity {

	// 아이디
	@Id
	@Column(name="member_id", length=20, nullable=false)
	private String id;
	
	// 비밀번호
	@Column(name="member_password", nullable=false)
	private String password;
	
	// 이름
	@Column(name="member_name", nullable=false)
	private String name;
	
	// 권한
	@Enumerated(EnumType.STRING)
	@Column(name="member_role", nullable=false)
	private MemberRole role;
	
	// 생성날짜
	@Column(name="member_create_date", nullable=false)
	private Date createDate;
	
}

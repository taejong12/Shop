package com.shop.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

// 상속관계 매핑이 아니다
// 자식 클래스에 매핑 정보만 제공
// 직접 생성해서 사용할 일이 없으므로 추상 클래스로 만드는 것을 권장
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {

	// insert 시 작동
	@CreationTimestamp
	@Column(updatable = false, nullable=false) // 업데이트 안됨
	private Date createdTime;
	
	// update 시 작동
	@UpdateTimestamp
	@Column(insertable=false) // 인서트 안됨
	private Date updatedTime;
}

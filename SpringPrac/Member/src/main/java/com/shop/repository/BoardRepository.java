package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entity.BoardEntity;

// Repository의 경우에는 JpaRepository를 extends 받으면 해당 Repository로 JPA의 대부분의 자동 쿼리 기능을 수행할 수 있다.
// 쿼리 정보 설정 @Repository는 JpaRepository가 해당하는 역할을 하고 있기 때문에 작성하지 않는다
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

	
}

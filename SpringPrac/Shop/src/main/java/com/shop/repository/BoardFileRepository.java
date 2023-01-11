package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entity.BoardFileEntity;

public interface BoardFileRepository extends JpaRepository<BoardFileEntity, Long>{
	
}

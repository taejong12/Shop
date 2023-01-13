package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entity.ItemFileEntity;

public interface ItemFileRepository extends JpaRepository<ItemFileEntity, Long>{

//	itemFileEntity findByitemNo(Long itemNo);

}

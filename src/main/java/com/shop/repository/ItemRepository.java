package com.shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shop.entity.ItemEntity;


// Repository의 경우에는 JpaRepository를 extends 받으면 해당 Repository로 JPA의 대부분의 자동 쿼리 기능을 수행할 수 있다.
// 쿼리 정보 설정 @Repository는 JpaRepository가 해당하는 역할을 하고 있기 때문에 작성하지 않는다
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

	// 조회수 증가시 쿼리
	// update item_table set item_hits=item_hits+1 where id=?
	// 쿼리 어노테이션 사용
	// 2가지 방식이 있다
	// 엔티티를 기준, 디비 기준(네이티브쿼리 트루)
	// 엔티티 이름 사용
	// 엔티티는 약어를 쓰는게 필수
	// 엔티티에 정의한 컬럼 기준으로 적어야 한다
	// :itemNo=@Param("itemNo") 과 매칭이 된다
	// 업데이트나 딜리트 쿼리를 수행할 때는 추가적으로 @Modifying 필수로 붙여야 한다
	@Modifying
	@Query(value="update ItemEntity b set b.itemHits=b.itemHits+1 where b.itemNo = :itemNo")
	void itemUpdateHits(@Param("itemNo") Long itemNo);
	
	Page<ItemEntity> findByItemTitleContainingOrItemContentContaining(String itemTitle, String itemContent, Pageable pageable);

}
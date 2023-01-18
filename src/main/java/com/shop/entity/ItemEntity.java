package com.shop.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.shop.dto.ItemDto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


// @Setter 엔티티에서 setter를 쓰지않고 생성자를 통해 파라미터를 설정하는게 좋다.
// set하는 부분이 여러곳으로 퍼져서 객체의 일관성을 보장할 수 없다.(에러찾기 힘듬,유지보수 힘듬)
// 엔티티를 변경해야 할 일이 있다면  비즈니스 메서드를 만들어서 사용한다.
@Getter
@Setter
// jpa에서는 생성자를 만들때 파라미터가 없는 생성자를 기본적으로 만들어야 한다.
// public, protected로 생성자 선언 가능(protected 권장)
// @NoArgsConstructor(access = AccessLevel.PROTECTED)를 사용함으로써 protected로 선언한 생성자 부분을 생략할 수 있습니다.(롬복에서 제공)
@NoArgsConstructor(access = AccessLevel.PROTECTED)

// 해당 class에 지정할 테이블명을 입력하고 테이블에 들어있는 컬럼을 Entity 클래스 안에 넣어야 한다.
@Entity

// 엔티티와 테이블 명을 다르게 하고 싶을 때는 @Table을 사용한다.
@Table(name="SHOP_ITEM")
public class ItemEntity extends BaseEntity{

	// @Id 어노테이션이 무조건 들어가야 하기 때문에 PK에 해당하는 컬럼에 넣어주면 된다.
	// 게시물 고유번호
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
			generator="SHOP_ITEM_SEQ_GEN" )
	@SequenceGenerator(
			name="SHOP_ITEM_SEQ_GEN", //시퀀스 제너레이터 이름
			sequenceName="SHOP_ITEM_SEQ", //시퀀스 이름
			initialValue=1, //시작값
			allocationSize=1 //메모리를 통해 할당할 범위 사이즈
			)
	private Long itemNo;

	// 상품명
	@Column(nullable = false)
	private String itemTitle;

	// 상품설명
	@Column(nullable = false)
	private String itemContent;

	// 상품가격
	@Column(nullable = false)
	private  int itemPrice;

	// 상품재고
	@Column(nullable = false)
	private int itemStock;

	// 상품조회수
	@Column
	private int itemHits;
	
	// 파일여부
	@Column
	private int itemFileAttached; // 1 or 0
	
	// 이미지 파일
	@OneToOne(mappedBy = "itemFileItemEntity", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private ItemFileEntity itemFileEntityList ;
	
	// 장바구니 조인
	@OneToMany(mappedBy = "itemEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<CartEntity> cartEntityList = new ArrayList<>();
	
	
	
	public static ItemEntity toWriteItemSaveEntity(ItemDto itemDto) {
		ItemEntity itemEntity = new ItemEntity();
		itemEntity.setItemTitle(itemDto.getItemTitle());
		itemEntity.setItemContent(itemDto.getItemContent());
		itemEntity.setItemPrice(itemDto.getItemPrice());
		itemEntity.setItemStock(itemDto.getItemStock());
		itemEntity.setItemHits(0);
		itemEntity.setItemFileAttached(0); // 파일 없음.
		
		return itemEntity;
	}

	public static ItemEntity toUpdateEntity(ItemDto itemDto) {
		ItemEntity itemEntity = new ItemEntity();
		itemEntity.setItemNo(itemDto.getItemNo());
		itemEntity.setItemTitle(itemDto.getItemTitle());
		itemEntity.setItemContent(itemDto.getItemContent());
		itemEntity.setItemPrice(itemDto.getItemPrice());
		itemEntity.setItemStock(itemDto.getItemStock());
		itemEntity.setItemHits(itemDto.getItemHits());
		itemEntity.setItemFileAttached(1); // 파일 있음.
		
		return itemEntity;
	}
	
	public static ItemEntity toSaveFileEntity(ItemDto itemDto) {
		ItemEntity itemEntity = new ItemEntity();
		itemEntity.setItemTitle(itemDto.getItemTitle());
		itemEntity.setItemContent(itemDto.getItemContent());
		itemEntity.setItemPrice(itemDto.getItemPrice());
		itemEntity.setItemStock(itemDto.getItemStock());
		itemEntity.setItemHits(0);
		itemEntity.setItemFileAttached(1); // 파일 있음.
		
		return itemEntity;
	}

	
//	public static itemEntity toUpdateFileEntity(itemDto itemDto) {
//		itemEntity itemEntity = new itemEntity();
//		itemEntity.setitemNo(itemDto.getitemNo());
//		itemEntity.setitemTitle(itemDto.getitemTitle());
//		itemEntity.setitemContent(itemDto.getitemContent());
//		itemEntity.setitemPrice(itemDto.getitemPrice());
//		itemEntity.setitemStock(itemDto.getitemStock());
//		itemEntity.setitemHits(0);
//		itemEntity.setitemFileAttached(1); // 파일 있음.
//		
//		return itemEntity;
//	}

	// Enum 값을 String으로 저장하고 싶을 때 사용
	// 이 어노테이션을 붙이지 않으면 정수가 저장된다.
	//	@Enumerated(EnumType.STRING)
	//	
	//	// 다대일 관계에서 사용한다.
	//	// fetch 속성은 글로벌 페치 전략을 설정할 때 사용하는데,
	//	// 실무에서 모든 연관관계는 지연로딩 LAZY로 설정해야 한다.
	//	// @XToOne 관계는 default가 즉시 로딩(EAGER)이므로 직접 지연 로딩(LAZY)으로 설정해야 한다.
	//	// 즉시 로딩은 예측이 어렵고, 어떤 SQL이 실행될 지 추적하기 어렵고 N+1문제가 자주 발생하기 때문이다.
	//	@ManyToOne(fetch = FetchType.LAZY)

	// 아이디
	// 외래키를 매핑할 때 사용한다.
	//	@JoinColumn(name="member_id", nullable = false)
	//	private MemberEntity memberEntity;


	//	SQL 사용 시 파라미터에 값을 쉽게 넣어주기 위한 어노테이션
	//	해당 클래스의 빌더 패턴 클래스를 생성합니다.
	//	생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함합니다.
	//	@Builder
	//	public itemEntity(long id, String title, String writer, String content) {
	//		this.id=id;
	//		this.title=title;
	//		this.writer=writer;
	//		this.content=content;
	//	}



}
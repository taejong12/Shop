package com.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="SHOP_ITEM_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemFileEntity extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
			generator="SHOP_ITEM_FILE_SEQ_GEN" )
	@SequenceGenerator(
		name="SHOP_ITEM_FILE_SEQ_GEN", //시퀀스 제너레이터 이름
		sequenceName="SHOP_ITEM_FILE_SEQ", //시퀀스 이름
		initialValue=1, //시작값
		allocationSize=1 //메모리를 통해 할당할 범위 사이즈
		)
	private Long itemFileNo;
	
	@Column
	private String itemFileOriginalFileName;

	@Column
	private String itemFileStoredFileName;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="item_no")
	private ItemEntity itemFileItemEntity;

	// 참조관계를 맺을 떄 pk값이 아니라 entity 값을 넘겨주는 특징이 있다
	// 주의할 부분 pk값이 아니라 부모 entity 값을 넘겨줘야 한다
	public static ItemFileEntity toItemFileEntity(ItemEntity itemEntity, String originalFileName, String storedFileName) {
		ItemFileEntity itemFileEntity = new ItemFileEntity();
		itemFileEntity.setItemFileItemEntity(itemEntity);
		itemFileEntity.setItemFileOriginalFileName(originalFileName);
		itemFileEntity.setItemFileStoredFileName(storedFileName);
		
		return itemFileEntity;
	}
	
	public static ItemFileEntity toItemFileUpdateEntity(Long itemFileNo,ItemEntity itemEntity, String originalFileName, String storedFileName) {
		ItemFileEntity itemFileEntity = new ItemFileEntity();
		itemFileEntity.setItemFileNo(itemFileNo);
		itemFileEntity.setItemFileItemEntity(itemEntity);
		itemFileEntity.setItemFileOriginalFileName(originalFileName);
		itemFileEntity.setItemFileStoredFileName(storedFileName);
		
		return itemFileEntity;
	}

	
}
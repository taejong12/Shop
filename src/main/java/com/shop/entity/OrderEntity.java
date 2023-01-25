package com.shop.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.shop.dto.OrderDto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="SHOP_ORDER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator="SHOP_ORDER_SEQ_GEN" )
	@SequenceGenerator(
			name="SHOP_ORDER_SEQ_GEN", //시퀀스 제너레이터 이름
			sequenceName="SHOP_ORDER_SEQ", //시퀀스 이름
			initialValue=1, //시작값
			allocationSize=1 //메모리를 통해 할당할 범위 사이즈
			)
	private Long orderNo;
	
	// 멤버 조인
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="member_no")
	private MemberEntity memberEntity;
	
	// 상품 조인
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="item_no")
	private ItemEntity itemEntity;
	
	private int itemCount;
	
	private int itemTotalPrice;
	
	private String itemPayStatus;

	public static OrderEntity toOrderSave(MemberEntity memberEntity, OrderDto orders, ItemEntity itemEntity) {
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setMemberEntity(memberEntity);
		orderEntity.setItemEntity(itemEntity);
		orderEntity.setItemCount(orders.getCartItemAmount());
		orderEntity.setItemTotalPrice(orders.getItemTotalPrice());
		orderEntity.setItemPayStatus(orders.getItemPayStatus());
		return orderEntity;
	}
	
	
	
	
	
	
	

}

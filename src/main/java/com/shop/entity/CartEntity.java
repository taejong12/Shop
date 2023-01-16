package com.shop.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.shop.dto.CartDto;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="SHOP_CART")
public class CartEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator="SHOP_CART_SEQ_GEN" )
	@SequenceGenerator(
			name="SHOP_CART_SEQ_GEN", //시퀀스 제너레이터 이름
			sequenceName="SHOP_CART_SEQ", //시퀀스 이름
			initialValue=1, //시작값
			allocationSize=1 //메모리를 통해 할당할 범위 사이즈
			)
	private Long cartNo;
	
	// 멤버 조인
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="member_no")
	private MemberEntity memberEntity ;
	
	// 상품 조인
	@OneToMany(mappedBy = "cartEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<ItemEntity> itemEntityList = new ArrayList<>();
	
	private int cartItemAmount;

	public static CartEntity toCartSaveEntity(CartDto cartDto) {
		CartEntity cartEntity = new CartEntity();
		cartEntity.setCartItemAmount(cartDto.getCartItemAmount());
		return cartEntity;
	}


	


}

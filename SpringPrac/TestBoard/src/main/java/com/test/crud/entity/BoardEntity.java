package com.test.crud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// @Setter 엔티티에서 setter를 쓰지않고 생성자를 통해 파라미터를 설정하는게 좋다.
// set하는 부분이 여러곳으로 퍼져서 객체의 일관성을 보장할 수 없다.(에러찾기 힘듬,유지보수 힘듬)
// 엔티티를 변경해야 할 일이 있다면  비즈니스 메서드를 만들어서 사용한다.
@Getter

// 빌더 패턴을 자동으로 생성해준다.
// builder() : 빌더 클래스의 인스턴스 생성
// build() : 실제 생성자 호출하여 객체 생성
@Builder

// 실제 값이 변경된 컬럼으로만 update 쿼리를 만들어주는 어노테이션
@DynamicUpdate

// 생성자를 자동 완성시켜주는 어노테이션
// @Builder와 @NoArgsConstructor를 함께 사용시 모든 매개 변수를 받는 생성자가 없으면 에러가 발생한다.
// 클래스에 존재하는 모든 필드에 대한 생성자를 자동으로 생성한다.
// 인스턴스 멤버의 선언 순서에 영향을 받기 때문에 변수의 순서를 바꾸면 생성자의 입력 값 순서도 바뀌게 되어 검출되지 않는 치명적인 오류를 발생시킬 수 있습니다.

@AllArgsConstructor 

// jpa에서는 생성자를 만들때 파라미터가 없는 생성자를 기본적으로 만들어야 한다.
// public, protected로 생성자 선언 가능(protected 권장)
// @NoArgsConstructor(access = AccessLevel.PROTECTED)를 사용함으로써 protected로 선언한 생성자 부분을 생략할 수 있습니다.(롬복에서 제공)
@NoArgsConstructor(access = AccessLevel.PROTECTED)

// 해당 class에 지정할 테이블명을 입력하고 테이블에 들어있는 컬럼을 Entity 클래스 안에 넣어야 한다.
@Entity

// 엔티티와 테이블 명을 다르게 하고 싶을 때는 @Table을 사용한다.
@Table(name="test_board")
public class BoardEntity{

	// @Id 어노테이션이 무조건 들어가야 하기 때문에 PK에 해당하는 컬럼에 넣어주면 된다.
	// 게시물 고유번호
	@Id 
	@Column(name="test_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator="TEST_BOARD_SEQ_GEN" )
	@SequenceGenerator(
            name="TEST_BOARD_SEQ_GEN", //시퀀스 제너레이터 이름
            sequenceName="TEST_BOARD_SEQ", //시퀀스 이름
            initialValue=1, //시작값
            allocationSize=1 //메모리를 통해 할당할 범위 사이즈
            )
	private Long id;
	
	// 게시물 제목
	@Column(name="title",nullable = false)
	private String title;
	
	// 게시물 내용
	@Column(name="content", nullable = false, columnDefinition = "TEXT")
	private String content;
	
	// 추천수
	@Column(name="writer", nullable = false)
	private String writer;
	
	
}

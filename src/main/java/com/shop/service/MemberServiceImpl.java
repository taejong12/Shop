package com.shop.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.shop.dto.MemberDto;
import com.shop.entity.MemberEntity;
import com.shop.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	// 회원가입
	@Override
	public void save(MemberDto memberDto) throws Exception {
		// repository의 save 메서드 호출 (조건. entity 객체를 넘겨줘야 함)
		// 첫번째로 할일은 dto를 entity로 변환하는 작업을 먼저 해줘야 한다.
		// 그러고 나서 두번째는 repository의 save 메서드를 호출하는 작업을 해줘야 한다.
		// 이것 말고도 다양한 방식이 있다.
		MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDto);
		memberRepository.save(memberEntity);
		
		
	}

	// 로그인
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		
		// 1.회원이 입력한 아이디로 DB에서 조회함.
		// 2.DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
		// 우리가 실제 사용할 것은 엔티티 객체인데 그 엔티티 객체를 옵션널 객체로 한 번 더 감싼 개념이다.
		// 포장지가 2개라고 보면 된다. 요렇게 활용한다.
		Optional<MemberEntity> byMemberId = memberRepository.findByMemberId(memberDto.getMemberId());
		if(byMemberId.isPresent()) {
			// 조회 결과가 있다.(해당 이메일을 가진 회원 정보가 있다.)
			// 옵션널로 감싸진 객체를 하나의 포장지를 벗겨내는 메서드 get() 그래서 메서드를 호출하게 되면 그 안에 있는 객체를 가져올 수 있다.
			// 안에 있는 엔티티 객체를 가져옴
			MemberEntity memberEntity = byMemberId.get();
			if(memberEntity.getMemberPassword().equals(memberDto.getMemberPassword())) {
				// 비밀번호 일치
				// 정보를 컨트롤러 쪽에 넘겨준다
				// Entity로 DB에서 조회해왔다
				// 컨트롤러로 넘겨줄 때는 Dto로 넘겨준다
				// 다양한 방법이 있다.
				// Entity 객체를 어디까지 가져가서 쓸 것인가 이번에는 service 클래스 안에 사용한다.
				// 컨트롤러에서는 Dto 객체를 사용해서 작업
				// 여기서는 Entity 객체를 Dto 객체로 변환한 후 리턴을 해줘야 한다
				// Dto 클래스로 가보자
				MemberDto loginSuccessDto = MemberDto.toMemberDto(memberEntity);
				return loginSuccessDto;
				
			} else {
				// 비밀번호 불일치(로그인 실패)
				return null;
			}
		} else {
			// 조회 결과가 없다.(해당 아이디를 가진 회원이 없다.)
			return null;
		}
		
	}

	// 회원 정보 페이지
	@Override
	public MemberDto memberInformationFindById(Long memberNo) throws Exception {
		Optional<MemberEntity> optionMemberEntity = memberRepository.findByMemberNo(memberNo);
		if(optionMemberEntity.isPresent()) {
			return MemberDto.toMemberDto(optionMemberEntity.get());
		} else {
			return null;
		}
	}

	// 회원 수정하기 페이지
	@Override
	public MemberDto memberUpdateFormFindById(Long memberNo) throws Exception {
		Optional<MemberEntity> optionMemberEntity = memberRepository.findByMemberNo(memberNo);
		if(optionMemberEntity.isPresent()) {
			return MemberDto.toMemberDto(optionMemberEntity.get());
		} else {
			return null;
		}
	}

	// 회원 정보 수정하기
	@Override
	public void memberUpdate(MemberDto memberDto) throws Exception {
		// 레포지터리에 update 메서드가 따로 없다
		// save 메서드를 사용한다
		// save 메서드는 아이디가 없으면 insert 쿼리 수행
		// DB에 Id가 있는 Entity 객체가 넘어오면 update 쿼리 수행
		memberRepository.save(MemberEntity.toMemberUpdateEntity(memberDto));
		
	}

	// 회원 탈퇴하기
	@Override
	public void memberDelete(Long memberNo) throws Exception {
		memberRepository.deleteById(memberNo);
	}

	// 회원가입 (이미 존재하는 아이디인지 확인) 아이디 중복확인
	@Override
	public MemberDto idCheck(String memberId) throws Exception {
		Optional<MemberEntity> byMemberId = memberRepository.findByMemberId(memberId);
		if(byMemberId.isPresent()) {
			// 조회결과가 있다 -> 사용할 수 없다.
			return MemberDto.toMemberDto(byMemberId.get());
		} else {
			// 조회결과가 없다 -> 사용할 수 있다.
			return null;
		}
	}
	

	// 회원가입 이메일 중복확인
	@Override
	public MemberDto emailCheck(String memberEmail) throws Exception {
		Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberEmail);
		if(byMemberEmail.isPresent()) {
			// 조회결과가 있다 -> 사용할 수 없다.
			return MemberDto.toMemberDto(byMemberEmail.get());
		} else {
			// 조회결과가 없다 -> 사용할 수 있다.
			return null;
		}
	}


}
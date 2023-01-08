package com.test.crud.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.test.crud.dto.MemberDto;
import com.test.crud.entity.MemberEntity;
import com.test.crud.repository.MemberRepository;
import com.test.crud.role.MemberRole;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService implements MemberServiceImpl {

	private final MemberRepository memberRepository;
	
	@Override
	public ResponseEntity signUp(MemberDto memberDto) {
		
		Optional<MemberEntity> memberEntity =memberRepository.findById(memberDto.getId());
		
		if(memberEntity.isEmpty()) {
			MemberEntity newMemberEntity = MemberEntity.builder()
					.id(memberDto.getId())
					.password(memberDto.getPassword())
					.name(memberDto.getName())
					.role(MemberRole.USER)
					.build();
			
			memberRepository.save(newMemberEntity);
			
			return new ResponseEntity("success", HttpStatus.OK);
		} else {
			
			return new ResponseEntity("fail", HttpStatus.OK);
		}
		
		

	}

	
}

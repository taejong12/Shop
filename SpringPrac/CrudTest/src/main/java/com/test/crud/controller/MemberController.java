package com.test.crud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.crud.dto.MemberDto;
import com.test.crud.entity.MemberEntity;
import com.test.crud.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

	private final MemberService memberService;
	
	// 회원가입 페이지
	@GetMapping("/signUp")
	public String signUpForm() {
		return "/member/signUp";
	}
	
	@PostMapping("/signUp")
	public ResponseEntity userSignUp(@RequestBody MemberDto memberDto) {
		return memberService.signUp(memberDto);
	}
	
	// 로그인 페이지
	@GetMapping("/login")
	public String loginForm() {
		return "/member/login";
	}
}

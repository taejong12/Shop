package com.shop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.dto.MemberDto;
import com.shop.service.MemberServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

	// 생성자 주입
	private final MemberServiceImpl memberService;
	
	// 회원가입 페이지 출력 요청
	@GetMapping("/signUp")
	public String signUpForm() {
		return "/member/signUp";
	}
	
	// 회원가입 정보입력 후
	@PostMapping("/signUp")
	public String userSignUp(@ModelAttribute MemberDto memberDto, RedirectAttributes ra) throws Exception {
		
		System.out.println("memberDto : " + memberDto);
		memberService.save(memberDto);
		
		// redirect 시 해당 값을 가지고 간다. (회원가입 성공시 이것을 활용해 alert 창 출력) 회원가입 성공 메시지
		ra.addFlashAttribute("msg", "signUpSuccess");
		
		return "redirect:/member/login";
	}
	
	// 로그인 페이지 출력 요청
	@GetMapping("/login")
	public String loginForm(Model model) throws Exception {
		
		return "/member/login";
	}
	
	@PostMapping("/loginCheck")
	public @ResponseBody MemberDto loginCheck(@RequestParam("memberId") String memberId) throws Exception {
		MemberDto memberDto = memberService.idCheck(memberId);
		
		return memberDto;
	}
	
	// 로그인을 하면 세션이라는 정보를 많이 사용한다
	// 포탈사이트에서 로그인하면 메일, 웹툰, 쇼핑 등 로그인 정보가 계속해서 유지하는 것은 세션을 활용한다
	// 로그인 처리
	@PostMapping("/login")
	public String loginCheck(@ModelAttribute MemberDto memberDto, HttpSession session, RedirectAttributes ra) throws Exception {
		MemberDto loginResult = memberService.login(memberDto);
		if (loginResult != null) {
			// login 성공
			session.setAttribute("memberNo", loginResult.getMemberNo());
			session.setAttribute("memberId", loginResult.getMemberId());
			session.setAttribute("memberPassword", loginResult.getMemberPassword());
			
			// 로그인 성공 메시지
			ra.addFlashAttribute("msg", "loginSuccess");
			return "redirect:/";
		} else {
			// login 실패	
			return "/member/login";
		}
		
	}
	
	// 회원정보 페이지
	@GetMapping("/information")
	public String memberInformationForm(HttpSession session, Model model) throws Exception {
		Long memberNo = (Long) session.getAttribute("memberNo");
		MemberDto memberDto = memberService.memberInformationFindById(memberNo);
		model.addAttribute("memberInformation", memberDto);
		return "/member/information";
	}
	
	// 회원정보 수정페이지
	@GetMapping("/update")
	public String memberUpdateForm(HttpSession session, Model model) throws Exception {
		Long memberNo = (Long) session.getAttribute("memberNo");
		MemberDto memberDto = memberService.memberUpdateFormFindById(memberNo);
		model.addAttribute("memberUpdate", memberDto);
		return "/member/update";
	}
	
	// 회원정보 수정 입력값 저장
	@PostMapping("/update")
	public String memberUpdate(@ModelAttribute MemberDto memberDto, RedirectAttributes ra) throws Exception {
		memberService.memberUpdate(memberDto);
		
		// 회원정보 수정 메시지
		ra.addFlashAttribute("msg", "memberUpdateSuccess");
		return "redirect:/member/information";
	}
	
	// 회원탈퇴
	@GetMapping("/delete")
	public String memberDelete(HttpSession session, RedirectAttributes ra) throws Exception {
		Long memberNo = (Long) session.getAttribute("memberNo");
		memberService.memberDelete(memberNo);
		
		// 세션에 저장되어있는 값 전부 삭제
		session.invalidate();
		
		// 회원탈퇴 메시지
		ra.addFlashAttribute("msg", "memberDeleteSuccess");
		
		return "redirect:/";
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public String memberLogout(HttpSession session, RedirectAttributes ra) {
		session.invalidate();
		
		// 로그아웃 메시지
		ra.addFlashAttribute("msg", "logoutSuccess");
		return "redirect:/";
	}
	
	// 아이디 중복확인
	@PostMapping("/idCheck")
	public @ResponseBody MemberDto idCheck(@RequestParam("memberId") String memberId) throws Exception {
		MemberDto memberDto = memberService.idCheck(memberId);
		
		return memberDto;
	}
	
	// 이메일 중복확인
	@PostMapping("/emailCheck")
	public @ResponseBody MemberDto emailCheck(@RequestParam("memberEmail") String memberEmail) throws Exception {
		MemberDto memberDto = memberService.emailCheck(memberEmail);
		return memberDto;
	}

	// 회원정보수정 비밀번호 검증 페이지
	@GetMapping("/updateCheck")
	public String updateContain() {
		return "/member/updateCheck";
	}

	// 회원탈퇴 비밀번호 검증 페이지
	@GetMapping("/deleteCheck")
	public String deleteContain() {
		
		return "/member/deleteCheck";
	}
	
	
}

package com.shop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.shop.login.LoginService;
import com.shop.service.UserService;

@Controller
public class UserInfoController {
	@Autowired
	private BCryptPasswordEncoder encodePwd;
	
	@Autowired
	private UserService userService;
	
	// 내 비밀번호, 닉네임 수정하기
	@PatchMapping("/user/info")
	public ResponseEntity<String> modifyInfo(String value, String valueType, String prevPassword, 
	        @AuthenticationPrincipal LoginService user, HttpSession session) {
	    // value = 변경할 값
	    // valueType = password, nickname, phone, email
	    String username = user.getUser().getUsername();
	    String msg = "";
	    
	    switch (valueType) {
	    case "password":
	        if(!encodePwd.matches(prevPassword, user.getPassword())) {
	            return new ResponseEntity<String>("현재 비밀번호가 일치하지 않습니다", HttpStatus.OK);
	        } 
	        value = encodePwd.encode(value);
	        msg = "비밀번호가 변경되었습니다";
	        break;
	        
	    case "nickname":
	        msg = "닉네임이 변경되었습니다";
	        break;
	    case "phone":
	        msg = "전화번호가 변경되었습니다";
	        break;
	    case "email":
	        msg = "이메일이 변경되었습니다";
	        break;
	    }
	    
	    userService.modifyInfo(username, valueType, value);
	    
//	    ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
//		HttpSession session = attr.getRequest().getSession();
		
		LoginService loginService = (LoginService) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(valueType.equals("nickname")) {
			loginService.getUser().setNickname(value);
		} 
		else  if(valueType.equals("password")) {
			loginService.getUser().setPassword(value);
		}
		else if(valueType.equals("point")) {
			int point = loginService.getUser().getPoint() + Integer.parseInt(value);
			loginService.getUser().setPoint(point);
		}
		else if(valueType.equals("phone")) {
			loginService.getUser().setPhone(value);
		}
		else if(valueType.equals("email")) {
			loginService.getUser().setEmail(value);
		}
	    
	    return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

}

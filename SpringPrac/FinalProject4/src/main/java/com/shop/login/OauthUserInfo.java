package com.shop.login;

import java.util.Map;
import java.util.UUID;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.shop.dto.JoinDTO;

@Service
public class OauthUserInfo {
		
	public JoinDTO createUser(String provider, String username, OAuth2User oauth2user) {
		UUID uid = UUID.randomUUID();
		BCryptPasswordEncoder encodePwd = new BCryptPasswordEncoder();
		String password = encodePwd.encode(uid.toString());
		String email = "";
		String phone = "";
		switch (provider) {
 
		case "google":
			email = oauth2user.getAttribute("email");
			phone = oauth2user.getAttribute("phone") == null ? "" : oauth2user.getAttribute("phone");
			break;
 
		case "naver":
			Map<String, Object> response = oauth2user.getAttribute("response");
			email = (String) response.get("email");
			phone = (String) response.get("mobile") == null ? "" : (String) response.get("mobile");
			break;
			
		case "kakao":
			Map<String, Object> kakaoAccount = oauth2user.getAttribute("kakao_account");
			email = (String) kakaoAccount.get("email");
			phone = oauth2user.getAttribute("phone") == null ? "" : oauth2user.getAttribute("phone");
			break;
		}
		
		//불러온 전화번호에서 - 제거
		phone = phone.replace("-", "");
		
		JoinDTO join = new JoinDTO();
		join.setUsername(username);
		join.setPassword(password);
		join.setEmail(email);
		join.setNickname(username);
		join.setPhone(phone);
		
		return join;
	}
	
	public String getUsername(String provider,OAuth2User oauth2user) {
		String id = "";
		switch (provider) {
 
		case "google":
			id = oauth2user.getAttribute("sub");
			break;
 
		case "naver":
			Map<String, Object> response = oauth2user.getAttribute("response");
			id = (String) response.get("id");
			break;
			
		case "kakao":
			id = oauth2user.getAttribute("id").toString();
			break;
			
		}
		return provider + "_" + id;
		
	}
 
}

package com.shop.login;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.shop.dao.UserDAO;
import com.shop.dto.JoinDTO;
import com.shop.dto.UserDTO;

@Service
public class OauthUserService extends DefaultOAuth2UserService {
 
	@Autowired
	private SqlSession sql;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private OauthUserInfo oauthUserInfo;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		OAuth2User oauth2user = super.loadUser(userRequest);
		
		String provider = userRequest.getClientRegistration().getRegistrationId();
		
		String username = oauthUserInfo.getUsername(provider, oauth2user);
		
		UserDTO user = sql.selectOne("user.login", username);
 
		if(user == null) {
			// 첫 로그인시 유저정보 생성
			JoinDTO join = oauthUserInfo.createUser(provider, username, oauth2user);
			userDAO.join(join);
			user = sql.selectOne("user.login", username);
		}
 		
		LoginService loginService = new LoginService();
		loginService.setUser(user);
		
		return loginService;
	}
 
	
	
}



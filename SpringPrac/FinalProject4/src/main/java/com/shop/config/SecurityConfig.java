package com.shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.shop.login.LoginDetailService;
import com.shop.login.LoginFailHandler;
import com.shop.login.LoginSuccessHandler;
import com.shop.login.OauthUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {
   
	@Autowired
	public LoginSuccessHandler loginSuccess;
	@Autowired
	public LoginFailHandler loginFail;
	@Autowired
	private LoginDetailService loginDetailService;
	@Autowired
	private OauthUserService oauthUserService;
	
   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
      http.authorizeRequests()
         .antMatchers("/user/**").hasAnyRole("USER","ADMIN")
         .antMatchers("/admin/**").hasRole("ADMIN")
         .anyRequest().permitAll();
      
      http.formLogin()
      	.loginPage("/") // 인증 필요한 페이지 접근시 이동페이지
		.loginProcessingUrl("/login")
      	.successHandler(loginSuccess)
      	.failureHandler(loginFail);
      
      http.logout()
		.logoutSuccessUrl("/myPage");
      
      http.rememberMe()
		.key("rememberKey")
		.rememberMeCookieName("rememberMeCookieName")
		.rememberMeParameter("remember-me")
		.tokenValiditySeconds(60 * 60 * 24 * 1)
		.userDetailsService(loginDetailService);
      
      http.oauth2Login()
      	.loginPage("/")
      	.successHandler(loginSuccess)
      	.userInfoEndpoint()
      	.userService(oauthUserService);
      
      http.csrf().disable();
      return http.build();         
   }
   
   @Bean
   public BCryptPasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
      //StandardPasswordEncoder()-SHA256
      //NoOpPasswordEncoder()-암호화하지 않은 데이터
   }
}

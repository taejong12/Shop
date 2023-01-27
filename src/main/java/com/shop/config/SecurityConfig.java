//package com.shop.config;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//	@Autowired
//	private DataSource dataSource;
//	
//	// 접근 설정
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http
//			// 주소 접근 권한
//			.authorizeHttpRequests()
//				.antMatchers("/", "/css/**", "/js/**", "/springboot_shop_item_img/**", "/member/**", "/item/detail/{itemNo}", "/item/paging").permitAll()
//				.anyRequest().authenticated()
//				.and()
//			// 로그인 폼 접근
//			.formLogin()
//				.loginPage("/member/login")
//				.permitAll()
//				.and()
//			// 로그아웃
//			.logout()
//				.logoutSuccessUrl("/")
//				.and()
//			// 헤더 토큰 x
//			.csrf().disable()
//			;
//
//		return http.build();
//	}
//	
//	// DB를 이용한 인증 설정
////	@Autowired
////	public void configureGlobal(AuthenticationManagerBuilder auth) 
////	  throws Exception {
////	    auth.jdbcAuthentication()
////	      .dataSource(dataSource)
////	      
////	      // 비밀번호 암호화 처리
//////	      .passwordEncoder(passwordEncoder())
////	      
////	      // 로그인 처리
////	      .usersByUsernameQuery("select member_id, member_password "
////	        + "from shop_member "
////	        + "where member_id = ?")
////	      
////	      // 권한 처리
////	      .authoritiesByUsernameQuery("select member_id, member_role "
////	        + "from shop_member "
////	        + "where member_role = ?");
////	}
////	
//	//	Authentication 로그인 관한 처리
//	//	Authroization 권한 설정
//	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//	    return new BCryptPasswordEncoder();
//	}
//	
//
//}
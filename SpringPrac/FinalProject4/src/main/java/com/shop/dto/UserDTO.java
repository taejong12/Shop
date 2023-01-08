package com.shop.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO implements Serializable {
	private long id;//고유 number
	private String username;//로그인 아이디
	private String password;
	private String email;
	private String nickname;//본명
	private int point;
	private String phone;
	private String rating;
	private String role;
	
	public UserDTO(String username, String password, String email, String nickname, String phone) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.nickname = nickname;
		this.phone = phone;
	}
}

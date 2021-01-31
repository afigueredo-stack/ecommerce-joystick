package com.afigueredo.joystick.security.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class TokenDto {

	private String token;

	public TokenDto() {
	}

	public TokenDto(String token) {
		this.token = token;
	}	
	
	@NotNull(message = "Usuário e/ou senha incorretos.")
	@Length(min = 1, message = "Foi possível adquirir o token de acesso.")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}

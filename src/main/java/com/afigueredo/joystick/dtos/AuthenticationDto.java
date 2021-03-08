package com.afigueredo.joystick.dtos;

import javax.validation.constraints.NotEmpty;

public class AuthenticationDto {

	private String usuario;
	private String senha;

	public AuthenticationDto() {
	}

	@NotEmpty(message = "Usuario não pode ficar vazio")
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String email) {
		this.usuario = email;
	}

	@NotEmpty(message = "Senha não pode ficar vazia.")
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "AuthenticationRequestDto [usuario=" + usuario + ", senha=" + senha + "]";
	}

}

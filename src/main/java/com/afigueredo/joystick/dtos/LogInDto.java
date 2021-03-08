package com.afigueredo.joystick.dtos;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.afigueredo.joystick.entities.Empresa;
import com.afigueredo.joystick.enums.PerfilEnum;

public class LogInDto {

	private Long id;
	private String usuario;
	private String nome;
	private Empresa empresa;
	private String token;

	public LogInDto() {
	}

	
	
	public LogInDto(Long id, String usuario, String nome, Empresa empresa, String token, PerfilEnum perfil) {
		this.id = id;
		this.usuario = usuario;
		this.nome = nome;
		this.empresa = empresa;
		this.token = token;
		this.perfil = perfil;
	}


	private PerfilEnum perfil;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	@NotNull(message = "Funcionario sem usuário.")
	@Length(min = 1, message = "Foi possível adquirir o token de acesso. Motivo: usuário vazio")
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	@NotNull(message = "Funcionario sem nome.")
	@Length(min = 1, message = "Foi possível adquirir o token de acesso. Motivo: nome vazio")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotNull(message = "Funcionario sem empresa.")
	@Length(min = 1, message = "Foi possível adquirir o token de acesso. Motivo: empresa vazio")
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@NotNull(message = "Funcionario sem perfil.")
	@Length(min = 1, message = "Foi possível adquirir o token de acesso. Motivo: perfil vazio")
	public PerfilEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}
	
	
	@NotNull(message = "Usuário e/ou senha incorretos.")
	@Length(min = 1, message = "Foi possível adquirir o token de acesso. Motivo: token inválido")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}

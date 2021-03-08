package com.afigueredo.joystick.dtos;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.afigueredo.joystick.entities.Empresa;
import com.afigueredo.joystick.enums.PerfilEnum;

public class FuncionarioDto {
	
	private Long id;
	private String nome;
	private String usuario;
	private String email;
	private Optional<String> senha = Optional.empty();
	private Optional<Empresa> empresa;
	private Optional<PerfilEnum> perfil;

	public FuncionarioDto() {
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
	@NotEmpty(message = "Empresa não pode ser vazia")
	public Optional<Empresa> getEmpresa() {
		return empresa;
	}


	public void setEmpresa(Optional<Empresa> empresa) {
		this.empresa = empresa;
	}


	@NotEmpty(message = "Nome não pode ser vazio")
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	public Optional<PerfilEnum> getPerfil() {
		return perfil;
	}


	public void setPerfil(Optional<PerfilEnum> perfil) {
		this.perfil = perfil;
	}


	@NotEmpty(message = "Nome do usuário não pode ser vazio")
	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	
	@NotEmpty(message = "Email não pode ser vazio")
	@Length(min=5, max=200,  message = "Email deve conter entre 5 e 200 caracteres")
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Optional<String> getSenha() {
		return senha;
	}


	public void setSenha(Optional<String> senha) {
		this.senha = senha;
	}


	@Override
	public String toString() {
		return "FuncionarioDto [id=" + id + ", nome=" + nome + ", usuario=" + usuario + ", email=" + email + ", senha="
				+ senha + ", empresa=" + empresa + ", perfil=" + perfil + "]";
	}	

	
}

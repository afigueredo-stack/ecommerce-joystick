package com.afigueredo.joystick.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.afigueredo.joystick.enums.PerfilEnum;

@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable {

  private static final long serialVersionUID = -5754246207015712518L;

  private Long id;
  private String nome;
  private String email;
  private String usuario;
  private String senha;
  private String cpf;
  private PerfilEnum perfil;
  private Date dataCriacao;
  private Date dataAtualizacao;
  private Empresa empresa;

  public Funcionario() {
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "nome", nullable = false)
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  @Column(name = "email", nullable = false)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Column(name = "cpf", nullable = false)
  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  @Column(name = "usuario", nullable = false)
  public String getUsuario() {
	return usuario;
  }

  public void setUsuario(String usuario) {
	this.usuario = usuario;
  }

  @Enumerated(EnumType.STRING)
  @Column(name = "perfil", nullable = false)
  public PerfilEnum getPerfil() {
    return perfil;
  }

  public void setPerfil(PerfilEnum perfil) {
    this.perfil = perfil;
  }

  @Column(name = "data_criacao", nullable = false)
  public Date getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(Date dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  @Column(name = "data_atualizacao", nullable = false)
  public Date getDataAtualizacao() {
    return dataAtualizacao;
  }

  public void setDataAtualizacao(Date dataAtualizacao) {
    this.dataAtualizacao = dataAtualizacao;
  }

  @Column(name = "senha", nullable = false)
  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  @ManyToOne(fetch = FetchType.EAGER)
  public Empresa getEmpresa() {
    return empresa;
  }

  public void setEmpresa(Empresa empresa) {
    this.empresa = empresa;
  }

  @PreUpdate
  public void preUpdate() {
    dataAtualizacao = new Date();
  }

  @PrePersist
  public void prePersist() {
    final Date atual = new Date();
    dataCriacao = atual;
    dataAtualizacao = atual;
  }

  @Override
  public String toString() {
    return "Funcionario [id=" + id + ", nome=" + nome + ", email=" + email + ", usuario=" + usuario + ", senha=" + senha + ", cpf=" + cpf 
    		+ ", perfil=" + perfil + ", dataCriacao=" + dataCriacao + ", dataAtualizacao="
        + dataAtualizacao + ", empresa=" + empresa + "]";
  }

}

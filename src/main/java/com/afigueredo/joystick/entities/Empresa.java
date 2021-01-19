package com.afigueredo.joystick.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "empresa")
public class Empresa implements Serializable {

  private static final long serialVersionUID = 7641006110435758780L;

  private Long codFilial;
  private String razaoSocial;
  private String nomeFantasia;
  private String cnpjCpf;
  private Date dataCriacao;
  private Date dataAtualizacao;

  public Empresa() {
  }

  @Id
  @Column(name = "cod_filial")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empresa_seq")
  @SequenceGenerator(name = "empresa_seq", sequenceName = "empresa_seq", allocationSize = 1)
  public Long getCodFilial() {
    return codFilial;
  }

  public void setCodFilial(Long codFilial) {
    this.codFilial = codFilial;
  }

  @Column(name = "razao_social", nullable = false)
  public String getRazaoSocial() {
    return razaoSocial;
  }

  public void setRazaoSocial(String razaoSocial) {
    this.razaoSocial = razaoSocial;
  }

  @Column(name = "nome_fantasia", nullable = false)
  public String getNomeFantasia() {
    return nomeFantasia;
  }

  public void setNomeFantasia(String nomeFantasia) {
    this.nomeFantasia = nomeFantasia;
  }

  @Column(name = "cnpjcpf", nullable = false)
  public String getCnpjCpf() {
    return cnpjCpf;
  }

  public void setCnpjCpf(String cnpjcpf) {
    this.cnpjCpf = cnpjcpf;
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
    return "Empresa [cnpjCpf=" + cnpjCpf + ", dataAtualizacao=" + dataAtualizacao + ", dataCriacao=" + dataCriacao
        + ", codFilial=" + codFilial + ", nomeFantasia=" + nomeFantasia + ", razaoSocial=" + razaoSocial + "]";
  }

}

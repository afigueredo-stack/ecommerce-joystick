package com.afigueredo.joystick.dtos;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

public class CadastroEmpresaDto {

  private Long codFilial;
  private String razaoSocial;
  private String nomeFantasia;
  private String cnpjCpf;

  public Long getCodFilial() {
    return codFilial;
  }

  public void setCodFilial(Long codFilial) {
    this.codFilial = codFilial;
  }

  @NotEmpty(message = "Razão social não pode ser vazio")
  @Length(min = 3, max = 200, message = "Razão social deve conter entre 3 e 200 caracteres.")
  public String getRazaoSocial() {
    return razaoSocial;
  }

  public void setRazaoSocial(String razaoSocial) {
    this.razaoSocial = razaoSocial;
  }

  @NotEmpty(message = "Nome fantasia não pode ser vazio")
  @Length(min = 3, max = 200, message = "Razão social deve conter entre 3 e 200 caracteres.")
  public String getNomeFantasia() {
    return nomeFantasia;
  }

  public void setNomeFantasia(String nomeFantasia) {
    this.nomeFantasia = nomeFantasia;
  }

  @NotEmpty(message = "CNPJ/CPF não pode ser vazio")
  @Length(min = 3, max = 200, message = "CNPJ/CPF deve conter entre 12 e 18 caracteres.")
  @CNPJ(message = "CNPJ inválido")
  public String getCnpjCpf() {
    return cnpjCpf;
  }

  public void setCnpjCpf(String cnpjCpf) {
    this.cnpjCpf = cnpjCpf;
  }

  @Override
  public String toString() {
    return "CadastroEmpresaDto [cnpjCpf=" + cnpjCpf + ", codFilial=" + codFilial + ", nomeFantasia=" + nomeFantasia
        + ", razaoSocial=" + razaoSocial + "]";
  }

}

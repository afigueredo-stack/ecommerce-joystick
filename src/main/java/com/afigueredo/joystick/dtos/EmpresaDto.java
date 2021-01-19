package com.afigueredo.joystick.dtos;

public class EmpresaDto {

  private Long codFilial;
  private String razaoSocial;
  private String nomeFantasia;
  private String cnpjCpf;

  public EmpresaDto() {
  }

  public Long getCodFilial() {
    return codFilial;
  }

  public void setCodFilial(Long codFilial) {
    this.codFilial = codFilial;
  }

  public String getRazaoSocial() {
    return razaoSocial;
  }

  public void setRazaoSocial(String razaoSocial) {
    this.razaoSocial = razaoSocial;
  }

  public String getNomeFantasia() {
    return nomeFantasia;
  }

  public void setNomeFantasia(String nomeFantasia) {
    this.nomeFantasia = nomeFantasia;
  }

  public String getCnpjCpf() {
    return cnpjCpf;
  }

  public void setCnpjCpf(String cnpjCpf) {
    this.cnpjCpf = cnpjCpf;
  }

  @Override
  public String toString() {
    return "EmpresaDTO [cnpjCpf=" + cnpjCpf + ", codFilial=" + codFilial + ", nomeFantasia=" + nomeFantasia
        + ", razaoSocial=" + razaoSocial + "]";
  }

}

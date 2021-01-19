package com.afigueredo.joystick.services;

import java.util.Optional;

import com.afigueredo.joystick.entities.Empresa;

public interface EmpresaService {
  /**
   * Retorna uma empresa dado um CNPJ.
   * 
   * @param cnpj
   * @return Optional<Empresa>
   */
  Optional<Empresa> buscarPorCnpjCpf(String cnpjCpf);

  /**
   * Cadastra uma nova empresa na base de dados.
   * 
   * @param empresa
   * @return Empresa
   */
  Empresa persistir(Empresa empresa);
}

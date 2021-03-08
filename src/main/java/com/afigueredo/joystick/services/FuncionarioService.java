package com.afigueredo.joystick.services;

import java.util.Optional;

import com.afigueredo.joystick.entities.Funcionario;

public interface FuncionarioService {

  /**
   * Persiste um funcionário na base de dados.
   * 
   * @param funcionario
   * @return Funcionario
   */
  Funcionario persistir(Funcionario funcionario);

  /**
   * Busca e retorna um funcionário dado um CPF.
   * 
   * @param cpf
   * @return Optional<Funcionario>
   */
  Optional<Funcionario> buscarPorCpf(String cpf);

  /**
   * Busca e retorna um funcionário dado um email.
   * 
   * @param email
   * @return Optional<Funcionario>
   */
  Optional<Funcionario> buscarPorEmail(String email);

  /**
   * Busca e retorna um funcionário por ID.
   * 
   * @param id
   * @return Optional<Funcionario>
   */
  Optional<Funcionario> buscarPorId(Long id);
  
  /**
   * Busca e retorna um funcionário por usuario.
   * 
   * @param usuario
   * @return Optional<Funcionario>
   */
  Optional<Funcionario> buscarPorUsuario(String usuario);

}

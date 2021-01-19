package com.afigueredo.joystick.repositories;

import com.afigueredo.joystick.entities.Empresa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

  @Transactional
  Empresa findByCnpjCpf(String cnpjCpf);

}

package com.afigueredo.joystick.services.impl;

import java.util.Optional;

import com.afigueredo.joystick.entities.Empresa;
import com.afigueredo.joystick.repositories.EmpresaRepository;
import com.afigueredo.joystick.services.EmpresaService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaServiceImpl implements EmpresaService {

  private static final Logger log = LoggerFactory.getLogger(EmpresaServiceImpl.class);

  @Autowired
  private EmpresaRepository empresaRepository;

  @Override
  public Optional<Empresa> buscarPorCnpjCpf(String cnpjCpf) {
    log.info("Buscando uma empresa para o CNPJ {}", cnpjCpf);

    return Optional.ofNullable(empresaRepository.findByCnpjCpf(cnpjCpf));
  }

  @Override
  public Empresa persistir(Empresa empresa) {
    log.info("Persistindo empresa: {}", empresa);
    return this.empresaRepository.save(empresa);
  }

}

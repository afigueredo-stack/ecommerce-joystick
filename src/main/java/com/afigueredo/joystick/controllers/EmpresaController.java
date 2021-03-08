package com.afigueredo.joystick.controllers;

import java.util.Optional;

import com.afigueredo.joystick.dtos.EmpresaDto;
import com.afigueredo.joystick.entities.Empresa;
import com.afigueredo.joystick.response.Response;
import com.afigueredo.joystick.services.EmpresaService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/ecommerce/empresa")
@CrossOrigin(origins = "*")
public class EmpresaController {

  private static final Logger log = LoggerFactory.getLogger(EmpresaController.class);

  @Autowired
  EmpresaService empresaService;

  public EmpresaController() {
  }

  /**
   * Retorna uma empresa
   * 
   * @param cnpjcpf
   * @return ResponseEntity<Response<EmpresaDto>
   */
  @GetMapping(value = "/consulta-empresa/{cnpjcpf}")
  public ResponseEntity<Response<EmpresaDto>> buscarPorCnpjCpf(@PathVariable("cnpjcpf") String cnpjCpf) {
    log.info("Buscando empresa por CNPJ: {}", cnpjCpf);
    Response<EmpresaDto> response = new Response<EmpresaDto>();
    Optional<Empresa> empresa = empresaService.buscarPorCnpjCpf(cnpjCpf);

    if (!empresa.isPresent()) {
      log.info("Empresa não encontrada para o CNPJ: {}", cnpjCpf);
      response.getErrors().add("Empresa não encontrada para o CNPJ " + cnpjCpf);
      return ResponseEntity.badRequest().body(response);
    }

    response.setData(this.converterEmpresaDto(empresa.get()));
    return ResponseEntity.ok(response);
  }

  private EmpresaDto converterEmpresaDto(Empresa empresa) {
    EmpresaDto empresaDto = new EmpresaDto();
    empresaDto.setCnpjCpf(empresa.getCnpjCpf());
    empresaDto.setCodFilial(empresa.getCodFilial());
    empresaDto.setRazaoSocial(empresa.getRazaoSocial());
    empresaDto.setNomeFantasia(empresa.getNomeFantasia());

    return empresaDto;
  }
}

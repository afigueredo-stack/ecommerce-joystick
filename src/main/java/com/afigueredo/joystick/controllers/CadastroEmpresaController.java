package com.afigueredo.joystick.controllers;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import com.afigueredo.joystick.dtos.CadastroEmpresaDto;
import com.afigueredo.joystick.entities.Empresa;
import com.afigueredo.joystick.response.Response;
import com.afigueredo.joystick.services.EmpresaService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ecommerce/cadastrar-empresa")
@CrossOrigin("*")
public class CadastroEmpresaController {

  private static final Logger log = LoggerFactory.getLogger(CadastroEmpresaController.class);

  @Autowired
  private EmpresaService empresaService;

  /**
   * Cadastro de empresa no sistema
   * 
   * @param cadastroEmpresaDtp
   * @param result
   * @return ResponseEntity<Reponse<CadatroEmpresaDto>>
   * @throws NoSuchAlgorithmException
   */
  @PostMapping
  public ResponseEntity<Response<CadastroEmpresaDto>> cadastrar(
      @Valid @RequestBody CadastroEmpresaDto cadastroEmpresaDto, BindingResult result) throws NoSuchAlgorithmException {
    log.info("Cadatrando empresa: {}", cadastroEmpresaDto.toString());
    Response<CadastroEmpresaDto> response = new Response<CadastroEmpresaDto>();

    validarDadosExistentes(cadastroEmpresaDto, result);

    Empresa empresa = this.converterDtoParaEmpresa(cadastroEmpresaDto);

    if (result.hasErrors()) {
      log.error("Erro validando dados de cadastro: {}", result.getAllErrors());
      result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
      return ResponseEntity.badRequest().body(response);
    }

    this.empresaService.persistir(empresa);

    response.setData(this.converterCadastroEmpresaDto(empresa));
    return ResponseEntity.ok(response);
  }

  /**
   * Verifica se a empresa já existe na base de dados.
   * 
   * @param cadastroPJDto
   * @param result
   */
  private void validarDadosExistentes(CadastroEmpresaDto cadastroEmpresaDto, BindingResult result) {
    this.empresaService.buscarPorCnpjCpf(cadastroEmpresaDto.getCnpjCpf())
        .ifPresent(emp -> result.addError(new ObjectError("empresa", "Empresa já existente.")));
  }

  /**
   * Converte os dados do DTO para empresa.
   * 
   * @param cadastroPJDto
   * @return Empresa
   */
  private Empresa converterDtoParaEmpresa(CadastroEmpresaDto cadastroEmpresaDto) {
    Empresa empresa = new Empresa();
    empresa.setCnpjCpf(cadastroEmpresaDto.getCnpjCpf());
    empresa.setRazaoSocial(cadastroEmpresaDto.getRazaoSocial());
    empresa.setNomeFantasia(cadastroEmpresaDto.getNomeFantasia());

    return empresa;
  }

  /**
   * Popula o DTO de cadastro da empresa.
   * 
   * @return CadastroPJDto
   */
  private CadastroEmpresaDto converterCadastroEmpresaDto(Empresa empresa) {
    CadastroEmpresaDto cadastroEmpresaDto = new CadastroEmpresaDto();
    cadastroEmpresaDto.setCodFilial(empresa.getCodFilial());
    cadastroEmpresaDto.setNomeFantasia(empresa.getNomeFantasia());
    cadastroEmpresaDto.setCnpjCpf(empresa.getCnpjCpf());
    cadastroEmpresaDto.setRazaoSocial(empresa.getRazaoSocial());

    return cadastroEmpresaDto;
  }
}

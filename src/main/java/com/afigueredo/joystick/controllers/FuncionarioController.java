package com.afigueredo.joystick.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.afigueredo.joystick.dtos.FuncionarioDto;
import com.afigueredo.joystick.entities.Funcionario;
import com.afigueredo.joystick.response.Response;
import com.afigueredo.joystick.services.FuncionarioService;

@RestController
@RequestMapping(path = "/ecommerce/funcionario")
@CrossOrigin(origins = "*")
public class FuncionarioController {
	
	private static final Logger log = LoggerFactory.getLogger(FuncionarioController.class);
	
	@Autowired
	FuncionarioService funcionarioService;
	
	public FuncionarioController() {}
	
	/** 
	 * Retorna um funcionario
	 * 
	 * @param id
	 * @return ResponseEntity<Response<FuncionarioDTO>
	 * 
	 * */
	
	@GetMapping(value = "/consulta-funcionario/{id}")
	public ResponseEntity<Response<FuncionarioDto>> buscarPorId(@PathVariable("id") Long id) {
		log.info("Buscando funcionário pelo id: {}", id);
		Response<FuncionarioDto> response = new Response<FuncionarioDto>();
		Optional<Funcionario> funcionario = funcionarioService.buscarPorId(id);
		
		if(!funcionario.isPresent()) {
			log.info("Funcionario não encontrado para esse id: {}", id);
			response.getErrors().add("Funcionario não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(this.converterFuncionarioDto(funcionario.get()));	
		return ResponseEntity.ok(response);		
	}

	private FuncionarioDto converterFuncionarioDto(Funcionario funcionario) {
		FuncionarioDto funcionarioDto = new FuncionarioDto();
		funcionarioDto.setId(funcionario.getId());
		funcionarioDto.setEmail(funcionario.getEmail());
		funcionarioDto.setNome(funcionario.getNome());
		funcionarioDto.setUsuario(funcionario.getUsuario());
		funcionarioDto.setEmpresa(Optional.of(funcionario.getEmpresa())); 
		funcionarioDto.setPerfil(Optional.ofNullable(funcionario.getPerfil())); 
		
		return funcionarioDto;
	}
	
	

}

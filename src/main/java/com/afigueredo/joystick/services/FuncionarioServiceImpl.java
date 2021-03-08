package com.afigueredo.joystick.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afigueredo.joystick.entities.Funcionario;
import com.afigueredo.joystick.repositories.FuncionarioRepository;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

	private static final Logger log = LoggerFactory.getLogger(FuncionarioServiceImpl.class);

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	public Funcionario persistir(Funcionario funcionario) {
		log.info("Persistindo funcionário: {}", funcionario);
		return this.funcionarioRepository.save(funcionario);
	}

	public Optional<Funcionario> buscarPorCpf(String cpf) {
		log.info("Buscando funcionário pelo CPF {}", cpf);
		return Optional.ofNullable(this.funcionarioRepository.findByCpf(cpf));
	}

	public Optional<Funcionario> buscarPorEmail(String email) {
		log.info("Buscando funcionário pelo email {}", email);
		return Optional.ofNullable(this.funcionarioRepository.findByEmail(email));
	}

	public Optional<Funcionario> buscarPorId(Long id) {
		log.info("Buscando funcionário pelo ID {}", id);
		return this.funcionarioRepository.findById(id);
	}

	public Optional<Funcionario> buscarPorUsuario(String usuario) {
		log.info("Buscando funcionário pelo usuario {}", usuario);
		return Optional.ofNullable(this.funcionarioRepository.findByUsuario(usuario));
	}

}

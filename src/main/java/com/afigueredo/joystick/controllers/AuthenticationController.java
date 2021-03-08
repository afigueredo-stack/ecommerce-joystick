package com.afigueredo.joystick.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.afigueredo.joystick.dtos.AuthenticationDto;
import com.afigueredo.joystick.dtos.LogInDto;
import com.afigueredo.joystick.dtos.TokenDto;
import com.afigueredo.joystick.entities.Funcionario;
import com.afigueredo.joystick.response.Response;
import com.afigueredo.joystick.services.FuncionarioService;
import com.afigueredo.joystick.utils.JwtTokenUtil;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

	private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);
	private static final String TOKEN_HEADER = "Authorization";
	private static final String BEARER_PREFIX = "Bearer ";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private FuncionarioService funcionarioService;

	/**
	 * Gera e retorna dados do usuário com token JWT.
	 * 
	 * @param authenticationDto
	 * @param result
	 * @return ResponseEntity<Response<LogInDto>>
	 * @throws AuthenticationException
	 * @throws IOException 
	 */
	@PostMapping
	public ResponseEntity<Response<LogInDto>> gerarDadosLogIn(@Valid @RequestBody AuthenticationDto authenticationDto,
			BindingResult result) throws AuthenticationException, NoSuchAlgorithmException {
		Response<LogInDto> response = new Response<LogInDto>();

		if (result.hasErrors()) {
			log.error("Erro validando Token para campos informandos: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		log.info("Gerando token para o usuario {}.", authenticationDto.getUsuario());

		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationDto.getUsuario(), authenticationDto.getSenha()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			

			UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDto.getUsuario());
			String token = jwtTokenUtil.obterToken(userDetails);

			log.info("Token gerado: {}", token);

			if (token == null) {
				log.error("Erro na validação do token que resultou em vazio: {}", result.getAllErrors());
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}

			response.setData(extrairDados(token, authenticationDto.getUsuario()));	

			return ResponseEntity.ok(response);	

		} catch (Exception e) {	
			log.error("Usuario e/ou senha incorreto(s). Exception: {}", result.getAllErrors());
			response.getErrors().add("Usuario e/ou senha incorreto(s)");
			return ResponseEntity.badRequest().body(response);			
		}	


	}


	/**
	 * Gera um novo token com uma nova data de expiração.
	 * 
	 * @param request
	 * @return ResponseEntity<Response<TokenDto>>
	 */
	@PostMapping(value = "/refresh")
	public ResponseEntity<Response<TokenDto>> gerarRefreshTokenJwt(HttpServletRequest request) {
		log.info("Gerando refresh token JWT.");
		Response<TokenDto> response = new Response<TokenDto>();
		Optional<String> token = Optional.ofNullable(request.getHeader(TOKEN_HEADER));

		if (token.isPresent() && token.get().startsWith(BEARER_PREFIX)) {
			token = Optional.of(token.get().substring(7));
		}

		if (!token.isPresent()) {
			response.getErrors().add("Token não informado.");
		} else if (!jwtTokenUtil.tokenValido(token.get())) {
			response.getErrors().add("Token inválido ou expirado.");
		}

		if (!response.getErrors().isEmpty()) {
			return ResponseEntity.badRequest().body(response);
		}

		String refreshedToken = jwtTokenUtil.refreshToken(token.get());
		response.setData(new TokenDto(refreshedToken));
		return ResponseEntity.ok(response);
	}


	/**
	 * Metodo para compactar as informações necessárias
	 * para o LogIn **/
	private LogInDto extrairDados(String token, String usuario) {
		log.info("Compactando dados no DTO...");
		LogInDto logInDto = new LogInDto();

		Optional<Funcionario> funcionario = funcionarioService.buscarPorUsuario(usuario);

		logInDto.setId(funcionario.get().getId());
		logInDto.setUsuario(funcionario.get().getUsuario());
		logInDto.setNome(funcionario.get().getNome());
		logInDto.setEmpresa(funcionario.get().getEmpresa());
		logInDto.setPerfil(funcionario.get().getPerfil());
		logInDto.setToken(token);

		return logInDto;
	}

}

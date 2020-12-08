package com.bip.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BipEmployeePointApplication {
	
	//@Value("${paginacao.qtd_por_pagina}")
	//private int intQtdPorPagina;
	//@Autowired
	//private UsuarioRepository usuarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(BipEmployeePointApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			System.out.println("Aplicação iniciada para execução de empregado.");
			
			/*Usuario usuario = new Usuario();
			usuario.setEmail("usuario@email.com");
			usuario.setUserTypeAccess(PerfilEnum.ROLE_USUARIO);
			usuario.setHashedPassword(SenhaUtils.gerarBCrypt("123456"));
			this.usuarioRepository.save(usuario);
			
			Usuario admin = new Usuario();
			admin.setEmail("admin@email.com");
			admin.setUserTypeAccess(PerfilEnum.ROLE_ADMIN);
			admin.setHashedPassword(SenhaUtils.gerarBCrypt("123456"));
			System.out.println("Senha :" + admin.getHashedPassword());
			this.usuarioRepository.save(admin);*/
			
		};
	}
}

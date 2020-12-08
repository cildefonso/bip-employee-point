package com.bip.api.security.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bip.api.security.entities.Usuario;

//@Transactional(readOnly = true)
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
	Usuario findByEmail(String email);
}

package com.spring.WEB2.service;

import java.util.List;

import com.spring.WEB2.model.Usuario;

public interface UsuarioService {
	
	List<Usuario> findAll();
	Usuario findByCpf( long cpf);
	Usuario save(Usuario usuario);
}

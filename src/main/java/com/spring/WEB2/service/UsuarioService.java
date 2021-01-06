package com.spring.WEB2.service;

import java.util.List;

import com.spring.WEB2.model.Usuario;
import com.spring.WEB2.model.UsuarioPerfil;

public interface UsuarioService {
	
	List<Usuario> findAll();
	Usuario findByCpf( long cpf);
	Usuario save(Usuario usuario);
	public boolean verifyIfExistsAndSave(long cpf, Usuario usuario, UsuarioPerfil usuarioPerfil, boolean isChange);
}

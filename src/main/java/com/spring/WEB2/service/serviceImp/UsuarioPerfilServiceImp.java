package com.spring.WEB2.service.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.WEB2.model.UsuarioPerfil;
import com.spring.WEB2.repository.UsuarioPerfilRepository;
import com.spring.WEB2.service.UsuarioPerfilService;

@Service
public class UsuarioPerfilServiceImp implements UsuarioPerfilService{

	@Autowired
	UsuarioPerfilRepository usuarioPerfilRepository;

}

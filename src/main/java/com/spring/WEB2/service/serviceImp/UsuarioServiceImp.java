package com.spring.WEB2.service.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.WEB2.model.Usuario;
import com.spring.WEB2.repository.UsuarioRepository;
import com.spring.WEB2.service.UsuarioService;

@Service
public class UsuarioServiceImp implements UsuarioService{
	
	@Autowired
	UsuarioRepository usuarioRepository;
	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario findByCpf(long cpf) {
		// TODO Auto-generated method stub
		return usuarioRepository.findByCpf(cpf);
	}

	@Override
	public Usuario save(Usuario usuario) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
		usuario.setSenha(passwordEncoder.encode(usuario.getPassword()));
		System.out.println(usuario.getCpf());
		return usuarioRepository.save(usuario);
	}
	
}

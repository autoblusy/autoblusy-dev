package com.spring.WEB2.service.serviceImp;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.WEB2.model.Usuario;
import com.spring.WEB2.model.UsuarioPerfil;
import com.spring.WEB2.repository.UsuarioPerfilRepository;
import com.spring.WEB2.repository.UsuarioRepository;
import com.spring.WEB2.service.UsuarioService;
import com.spring.WEB2.utils.StringUtils;

@Service
public class UsuarioServiceImp implements UsuarioService{
	
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	UsuarioPerfilRepository usuarioPerfilRepository;
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
		return usuarioRepository.save(usuario);
	}

	@Override
	public boolean verifyIfExistsAndSave(long cpf, Usuario usuario, UsuarioPerfil perfilUsuario) {
		Usuario user = findByCpf(cpf);
		if(user == null) {
			perfilUsuario.setUltimoAcesso((new Date(System.currentTimeMillis())).toString());
			UsuarioPerfil result = usuarioPerfilRepository.save(perfilUsuario);
			usuario.setUsuarioPerfil(result);
			usuario.setSenha(StringUtils.createPasswordWithSecurity(usuario.getPassword()));
			usuarioRepository.save(usuario);
			return true;
		} else {
			return false;
		}
	}
	
}

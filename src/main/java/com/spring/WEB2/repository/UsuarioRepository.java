package com.spring.WEB2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.WEB2.model.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	public Usuario findByCpf(long cpf);
}

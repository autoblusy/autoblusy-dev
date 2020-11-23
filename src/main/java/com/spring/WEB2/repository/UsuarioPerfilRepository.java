package com.spring.WEB2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.WEB2.model.UsuarioPerfil;


@Repository
public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfil, Long>{

}

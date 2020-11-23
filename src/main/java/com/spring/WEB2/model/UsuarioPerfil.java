package com.spring.WEB2.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class UsuarioPerfil {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario_perfil")
	private long id;
	
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn( name = "id_usuario")
	private Usuario usuario;
	
	private String nome;
	
	@Column(name = "foto_usuario")
	private String fotoUsuario;
	
	@Column(name = "cor_usuario")
	private String corUsuario;
	
	@Column(name = "ultimo_acesso")
	private String ultimoAcesso;

}

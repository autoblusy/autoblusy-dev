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
	
	
	public UsuarioPerfil() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario_perfil")
	private long id;
	
	
	private String nome;
	
	@Column(name = "foto_usuario")
	private String fotoUsuario;
	
	@Column(name = "cor_usuario")
	private String corUsuario;
	
	@Column(name = "ultimo_acesso")
	private String ultimoAcesso;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFotoUsuario() {
		return fotoUsuario;
	}

	public void setFotoUsuario(String fotoUsuario) {
		this.fotoUsuario = fotoUsuario;
	}

	public String getCorUsuario() {
		return corUsuario;
	}

	public void setCorUsuario(String corUsuario) {
		this.corUsuario = corUsuario;
	}

	public String getUltimoAcesso() {
		return ultimoAcesso;
	}

	public void setUltimoAcesso(String ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}

	
}

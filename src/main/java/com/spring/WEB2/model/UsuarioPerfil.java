package com.spring.WEB2.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	
	@Column(name = "dica1")
	private String dica1;
	
	@Column(name = "dica2")
	private String dica2;
	
	
	@Column(name = "senha_recuperacao_1")
	private String senha_recuperacao1;
	
	@Column(name = "senha_recuperacao_2")
	private String senha_recuperacao2;


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
		String ano =  this.ultimoAcesso.substring(0, 4);
		String mes =  this.ultimoAcesso.substring(5, 7);
		String dia = this.ultimoAcesso.substring(8, 10);
		return new String(dia + "/"+mes+"/"+ano);
	}

	public void setUltimoAcesso(String ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}

	public String getDica1() {
		return dica1;
	}

	public void setDica1(String dica1) {
		this.dica1 = dica1;
	}

	public String getDica2() {
		return dica2;
	}

	public void setDica2(String dica2) {
		this.dica2 = dica2;
	}

	public String getSenha_recuperacao1() {
		return senha_recuperacao1;
	}

	public void setSenha_recuperacao1(String senha_recuperacao1) {
		this.senha_recuperacao1 = senha_recuperacao1;
	}

	public String getSenha_recuperacao2() {
		return senha_recuperacao2;
	}

	public void setSenha_recuperacao2(String senha_recuperacao2) {
		this.senha_recuperacao2 = senha_recuperacao2;
	}
	
	

	
	
}

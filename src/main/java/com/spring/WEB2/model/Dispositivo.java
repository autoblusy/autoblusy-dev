package com.spring.WEB2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dispositivos")
public class Dispositivo {
	public Dispositivo() {
		// TODO Auto-generated constructor stub
	}
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO	)
	@Column(name = "id_dispositivo")
	private Long id;
	
	private String nome;
	
	private String endereco;
	
	private boolean status;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}

package com.spring.WEB2.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="usuarios")
public class Usuario implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3587055145212291923L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private long id;
	
	
	private long cpf;
	
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn( name = "id_usuario_perfil")
	private UsuarioPerfil usuarioPerfil;
	
	private String senha;
	
	private boolean admin;
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean getAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	 public Collection<? extends GrantedAuthority> getAuthorities() {
	        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
	        list.add(new SimpleGrantedAuthority( (this.getAdmin() == true)? "ADMIN":"USER" ));
	        System.out.println(this.admin);
	        System.out.println(list.get(0));
	        return list;
	    }

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;
	}

	@Override
	public String getUsername() {
		return Long.toString(this.cpf);
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public UsuarioPerfil getUsuarioPerfil() {
		return usuarioPerfil;
	}

	public void setUsuarioPerfil(UsuarioPerfil usuarioPerfil) {
		this.usuarioPerfil = usuarioPerfil;
	}
	
	
	
}

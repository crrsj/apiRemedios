package com.controle.remedios.usuarios;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "usuarios")
@Table(name = "Usuario")
public class Usuario implements UserDetails{
@Id	@GeneratedValue(strategy= GenerationType.IDENTITY)	
private Long id;
private String login;
private String senha;
public Usuario(Long id, String login, String senha) {
	super();
	this.id = id;
	this.login = login;
	this.senha = senha;
}
public Usuario() {
	super();
}


public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getLogin() {
	return login;
}
public void setLogin(String login) {
	this.login = login;
}
public String getSenha() {
	return senha;
}
public void setSenha(String senha) {
	this.senha = senha;
}
@Override
public int hashCode() {
	return Objects.hash(id, login, senha);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Usuario other = (Usuario) obj;
	return Objects.equals(id, other.id) && Objects.equals(login, other.login) && Objects.equals(senha, other.senha);
}
@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
	// TODO Auto-generated method stub
	return List.of(new SimpleGrantedAuthority("ROLE_USER"));//ESTUDAR ESSE CODIGO(CONT.DE PERFIS)
}
@Override
public String getPassword() {
	// TODO Auto-generated method stub
	return senha;
}
@Override
public String getUsername() {
	// TODO Auto-generated method stub
	return login;
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



}

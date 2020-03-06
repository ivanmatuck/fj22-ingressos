package br.com.caelum.ingresso.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements UserDetails {

	// atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String email;
	private String password;
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Permissao> permissoes = new HashSet<>();

	// construtores
	/**
	 * @deprecated hibernate only
	 */
	public Usuario() {
	}

	public Usuario(String email, String password, Set<Permissao> permissoes) {
		this.email = email;
		this.password = password;
		this.permissoes = permissoes;
	}

	// métodos
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return permissoes;
	}

	// getters and setters
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
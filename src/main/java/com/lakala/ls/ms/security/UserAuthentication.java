package com.lakala.ls.ms.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.lakala.ls.ms.domain.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.lakala.ls.ms.domain.User;

public class UserAuthentication implements Authentication {

	private static final long serialVersionUID = -6828207389247945333L;
	private final User user;
	private boolean authenticated = true;

	public UserAuthentication(User user) {
		this.user = user;
	}

	@Override
	public String getName() {
		return user.getUserName();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(Role role : user.getRoles()){
			authorities.add(new SimpleGrantedAuthority(role.getCode()));
		}
		return authorities;
	}

	@Override
	public Object getCredentials() {
		return user.getPassword();
	}

	@Override
	public User getDetails() {
		return user;
	}

	@Override
	public Object getPrincipal() {
		return user.getUserName();
	}

	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}

	@Override
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
}

package com.lakala.ls.ms.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lakala.ls.ms.domain.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

public class StatelessLoginFilter extends
		AbstractAuthenticationProcessingFilter {

	private final UserDetailsService userDetailsService;

	public StatelessLoginFilter(String urlMapping,
			UserDetailsService userDetailsService,
			AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(urlMapping, HttpMethod.POST.toString()));
		this.userDetailsService = userDetailsService;
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException,
			IOException, ServletException {

		if(request.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.toString())){
			return null;
		}
		final User user = new ObjectMapper().readValue(
				request.getInputStream(), User.class);

		final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(
				user.getUserName(), user.getPassword());
		Authentication authentication = getAuthenticationManager().authenticate(loginToken);
		return authentication;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {

		// Lookup the complete User object from the database and create an
		// Authentication for it
		final User authenticatedUser = userDetailsService
				.loadUserByname(authentication.getName());
		final UserAuthentication userAuthentication = new UserAuthentication(
				authenticatedUser);
		// Add the authentication to the Security context
		HttpSession session = request.getSession(true);
		session.setAttribute("user", new ObjectMapper().writeValueAsString(authenticatedUser));
		addCorsHeader(request, response);
		SecurityContextHolder.getContext()
				.setAuthentication(userAuthentication);
	}

	private void addCorsHeader(HttpServletRequest request, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", request.getHeader(HttpHeaders.ORIGIN));
		response.addHeader("Access-Control-Allow-Credentials","true");
		response.addHeader("Access-Control-Expose-Headers","x-auth-token");
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
											  AuthenticationException failed) throws IOException, ServletException {
		addCorsHeader(request, response);
		super.unsuccessfulAuthentication(request, response, failed);
	}
}
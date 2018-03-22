package com.lakala.ls.ms.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lakala.ls.ms.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.session.web.http.HttpSessionStrategy;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class StatelessAuthenticationFilter extends GenericFilterBean {

	private HttpSessionStrategy httpSessionStrategy;

	private SessionRepository sessionRepository;

	public StatelessAuthenticationFilter(HttpSessionStrategy httpSessionStrategy,SessionRepository sessionRepository){
		this.httpSessionStrategy = httpSessionStrategy;
		this.sessionRepository = sessionRepository;
	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
//		HttpSession session = request.getSession(false);
//		String sessionId = httpSessionStrategy.getRequestedSessionId(request);
//		if(sessionId!=null){
//			Session session = sessionRepository.getSession(sessionId);
//			if (session != null) {
//				User user = new ObjectMapper().readValue(
//						(String) session.getAttribute("user"), User.class);
//				UserAuthentication userAuthentication = new UserAuthentication(
//						user);
//				SecurityContextHolder.getContext().setAuthentication(userAuthentication);
//			}
//		}

		chain.doFilter(req, res); // always continue
	}
}
package com.lakala.ls.ms.config;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lakala.ls.ms.domain.Jnl;
import com.lakala.ls.ms.service.JnlService;

public class UserInterceptor  implements HandlerInterceptor{

	Logger log =LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JnlService jnlservice;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			log.info("==="+request.getRequestURL());
			
	        String uri = request.getRequestURI();
	        String ip =getIpAddr(request);
	        request.setAttribute("user_ip", ip);
	        String agent = request.getHeader("user-agent");
	        String refer = request.getHeader("referer");
	        if(refer.contains("los")){
	        	Jnl jnl = new Jnl();
	        	jnl.setAgent(agent);
	        	jnl.setIp(ip);
	        	jnl.setRefer(refer);
	        	jnl.setUri(uri);
	        	if(jnlservice==null){
	        		log.info("service is null");	
		        	BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()); 
		        	jnlservice = (JnlService) factory.getBean("jnlService"); 
	        	}
	        	jnlservice.addJnl(jnl);
	        	
	        	
	        	
	        }
	        
	        
	        Enumeration e= request.getHeaderNames();
	        while (e.hasMoreElements()) {
	            Object o= e.nextElement();
	           log.info(o+"==="+request.getHeader((String)o));
	        }
	
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	public static String getIpAddr(HttpServletRequest request) {
		
		String ip = request.getHeader("X-Forwarded-For");
        if (ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("HTTP_CLIENT_IP");
        }if (ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
		return ip;
	}

}

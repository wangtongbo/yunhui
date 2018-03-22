package com.lakala.ls.ms;

import com.lakala.ls.ms.domain.User;
import com.lakala.ls.ms.security.UserAuthentication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Current {
	
	public static User get(){
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication instanceof UserAuthentication)
			return (User)authentication.getDetails();
		return null;
	}
	
}

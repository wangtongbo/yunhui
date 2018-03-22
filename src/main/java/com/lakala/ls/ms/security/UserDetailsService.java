package com.lakala.ls.ms.security;

import java.util.ArrayList;
import java.util.List;

import com.lakala.ls.ms.domain.Role;
import com.lakala.ls.ms.domain.User;
import com.lakala.ls.ms.mapper.RoleMapper;
import com.lakala.ls.ms.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsService implements
		org.springframework.security.core.userdetails.UserDetailsService {

	private static final Logger logger = LoggerFactory
			.getLogger(UserDetailsService.class);


	@Autowired
	private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;


	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		User user = userMapper.loadUserByName(userName);

        if(user==null){
            throw new UsernameNotFoundException("user_not_found");
        }
        List<Role> roles = roleMapper.getByUserId(user.getId());
        user.setRoles(roles);
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getCode()));
        }

		return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),authorities);
	}

    public User loadUserByname(String userName){
        User user = userMapper.loadUserByName(userName);
        if(user==null){
            throw new UsernameNotFoundException("user_not_found");
        }
        List<Role> roles = roleMapper.getByUserId(user.getId());
        user.setRoles(roles);
        return user;
    }

}

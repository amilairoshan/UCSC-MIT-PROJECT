package com.tm.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.tm.model.UserRole;
import com.tm.model.Users;

public class UserPrincipal implements UserDetails {

	private Users users;
	
	public UserPrincipal(Users users) {
		super();
		this.users = users;
	}

	/*@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("USER"));
	}
*/
	
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<UserRole> roles = users.getUser_role();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
         
        for (UserRole role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole_name()));
        }
         
        return authorities;
    }
	
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return users.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return users.getUserName();
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

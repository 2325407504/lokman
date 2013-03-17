package com.aripd.account.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.account.domain.Account;
import com.aripd.account.domain.Role;
import com.aripd.account.repository.AccountRepository;

/**
 * A custom {@link UserDetailsService} where user information
 * is retrieved from a JPA repository
 */
@Service("userDetailsService")
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private AccountRepository repository;

	/**
	 * Returns a populated {@link UserDetails} object. 
	 * The username is first retrieved from the database and then mapped to 
	 * a {@link UserDetails} object.
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Account account = repository.findByUsernameOrEmail(username, username);
			
			Collection<GrantedAuthority> grantedAuthorities = toGrantedAuthorities(this.getCodes(account));
			boolean enabled = account.isActive();
			boolean accountNonExpired = true;
			boolean credentialsNonExpired = true;
			boolean accountNonLocked = true;
			
			return new User(
					account.getUsername(), 
					account.getPassword(),
					enabled,
					accountNonExpired,
					credentialsNonExpired,
					accountNonLocked,
					grantedAuthorities);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Collection<GrantedAuthority> toGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> result = new ArrayList<GrantedAuthority>();

		for (String role : roles) {
			result.add(new SimpleGrantedAuthority(role));
		}

		return result;
	}
	
	public List<String> getCodes(final Account account) {
		List<String> roleNames = new ArrayList<String>();

		for (Role role : account.getRoles()) {
			roleNames.add(role.getCode());
		}
		return roleNames;
	}

}

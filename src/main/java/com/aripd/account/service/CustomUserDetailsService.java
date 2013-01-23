package com.aripd.account.service;

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
import com.aripd.account.helper.AccountHelper;
import com.aripd.account.repository.AccountRepository;

/**
 * A custom {@link UserDetailsService} where user information
 * is retrieved from a JPA repository
 */
@Service("customUserDetailsService")
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private AccountRepository repository;

	@Autowired
	private AccountHelper accountHelper;
	
	/**
	 * Returns a populated {@link UserDetails} object. 
	 * The username is first retrieved from the database and then mapped to 
	 * a {@link UserDetails} object.
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Account account = repository.findOneByUsername(username);
			
			Collection<GrantedAuthority> grantedAuthorities = toGrantedAuthorities(accountHelper.getCodes(account));
			boolean enabled = account.getIsEnabled();
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
		List<GrantedAuthority> result = new ArrayList();

		for (String role : roles) {
			result.add(new SimpleGrantedAuthority(role));
		}

		return result;
	}
	
}

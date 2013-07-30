package com.aripd.member.service.impl;

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

import com.aripd.member.domain.Member;
import com.aripd.member.domain.Role;
import com.aripd.member.repository.MemberRepository;

/**
 * A custom {@link UserDetailsService} where user information is retrieved from
 * a JPA repository
 */
@Service("userDetailsService")
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MemberRepository repository;

    /**
     * Returns a populated {@link UserDetails} object. The username is first
     * retrieved from the database and then mapped to a {@link UserDetails}
     * object.
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Member member = repository.findByUsernameOrEmail(username, username);

            Collection<GrantedAuthority> grantedAuthorities = toGrantedAuthorities(this.getCodes(member));
            boolean enabled = member.isActive();
            boolean memberNonExpired = true;
            boolean credentialsNonExpired = true;
            boolean memberNonLocked = true;

            return new User(
                    member.getUsername(),
                    member.getPassword(),
                    enabled,
                    memberNonExpired,
                    credentialsNonExpired,
                    memberNonLocked,
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

    public List<String> getCodes(final Member member) {
        List<String> roleNames = new ArrayList<String>();

        for (Role role : member.getRoles()) {
            roleNames.add(role.getCode());
        }
        return roleNames;
    }
}

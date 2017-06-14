package com.atms.service.impl;

import com.atms.model.Developer;
import com.atms.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Alex Kazanovskiy
 */

@Service
public class DeveloperDetailsServiceImpl implements UserDetailsService {

    private final DeveloperRepository developerRepository;

    @Autowired
    public DeveloperDetailsServiceImpl(DeveloperRepository developerService) {
        this.developerRepository = developerService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        Developer userFromDatabase = developerRepository.findByNickname(login);

        if (userFromDatabase == null)
            userFromDatabase = developerRepository.findByEmail(login);


        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (GrantedAuthority authority : userFromDatabase.getAuthorities()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getAuthority());
            grantedAuthorities.add(grantedAuthority);
        }
        return new org.springframework.security.core.userdetails.User(userFromDatabase.getNickname(),
                userFromDatabase.getPassword(), grantedAuthorities);

    }
}

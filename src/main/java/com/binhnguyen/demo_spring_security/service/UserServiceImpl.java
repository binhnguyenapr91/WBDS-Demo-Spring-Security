package com.binhnguyen.demo_spring_security.service;

import com.binhnguyen.demo_spring_security.model.AppRole;
import com.binhnguyen.demo_spring_security.model.AppUser;
import com.binhnguyen.demo_spring_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByUsername(username);
        if (appUser == null){
            throw new UsernameNotFoundException("User Not Found");
        }
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        Set<AppRole> appRoles = appUser.getRoles();
        for(AppRole role : appRoles){
            grantedAuthoritySet.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(appUser.getUsername(),appUser.getPassword(),grantedAuthoritySet);
    }
}

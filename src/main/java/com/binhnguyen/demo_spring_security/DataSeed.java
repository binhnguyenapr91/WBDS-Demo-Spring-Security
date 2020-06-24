package com.binhnguyen.demo_spring_security;

import java.util.HashSet;

import com.binhnguyen.demo_spring_security.model.AppRole;
import com.binhnguyen.demo_spring_security.model.AppUser;
import com.binhnguyen.demo_spring_security.repository.RoleRepository;
import com.binhnguyen.demo_spring_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class DataSeed implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        // Roles
        if (roleRepository.findByName("ROLE_ADMIN") == null) {
            roleRepository.save(new AppRole("ROLE_ADMIN"));
        }

        if (roleRepository.findByName("ROLE_MEMBER") == null) {
            roleRepository.save(new AppRole("ROLE_MEMBER"));
        }

        // Admin account
        if (userRepository.findByUsername("admin") == null) {
            AppUser admin = new AppUser();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("123456"));
            HashSet<AppRole> roles = new HashSet<>();
            roles.add(roleRepository.findByName("admin"));
            roles.add(roleRepository.findByName("member"));
            admin.setRoles(roles);
            userRepository.save(admin);
        }

        // Member account
        if (userRepository.findByUsername("member") == null) {
            AppUser user = new AppUser();
            user.setUsername("member");
            user.setPassword(passwordEncoder.encode("123456"));
            HashSet<AppRole> roles = new HashSet<>();
            roles.add(roleRepository.findByName("member"));
            user.setRoles(roles);
            userRepository.save(user);
        }
    }

}

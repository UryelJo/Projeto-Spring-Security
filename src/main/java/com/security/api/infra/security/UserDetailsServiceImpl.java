package com.security.api.infra.security;

import com.security.api.domain.enums.UserRole;
import com.security.api.infra.persistence.entity.UserEntityJpa;
import com.security.api.infra.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntityJpa user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return UserEntityJpa.builder()
                .login(user.getLogin())
                .password(user.getPassword())
                .role(UserRole.valueOf("ADMIN"))
                .build();
    }
}

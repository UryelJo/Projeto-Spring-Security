package com.security.api.utils.config.bean;

import com.security.api.application.gateway.TokenEncoderAdapter;
import com.security.api.application.gateway.UsuarioGateway;
import com.security.api.utils.security.SecurityConfiguration;
import com.security.api.utils.security.SecurityFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityBeanConfiguration {
    @Bean
    protected AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilter securityFilter(TokenEncoderAdapter tokenEncoderAdapter, UsuarioGateway userGateway) {
       return new SecurityFilter(tokenEncoderAdapter, userGateway);
    }

    @Bean
    public SecurityConfiguration securityConfiguration(SecurityFilter securityFilter) {
        return new SecurityConfiguration(securityFilter);
    }
}

package com.security.api.utils.security;

import com.security.api.application.gateway.TokenEncoderAdapter;
import com.security.api.application.gateway.UsuarioGateway;
import com.security.api.infra.persistence.mapper.UserMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenEncoderAdapter tokenEncoderAdapter;
    private final UsuarioGateway userGateway;

    @Autowired
    public SecurityFilter(TokenEncoderAdapter tokenEncoderAdapter, UsuarioGateway userGateway) {
        this.tokenEncoderAdapter = tokenEncoderAdapter;
        this.userGateway = userGateway;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String token = this.getToken(request);
        if (token != null) {
            String tokenValidado = this.tokenEncoderAdapter.validar(token);
            UserDetails userDetails = UserMapper.toEntity(Objects.requireNonNull(this.userGateway.findUserByLogin(tokenValidado)
                    .orElse(null)));
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            return token.substring(7);
        }
        return null;
    }
}

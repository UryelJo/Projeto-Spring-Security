package com.security.api.infra.persistence.gateways;

import com.security.api.application.gateway.UsuarioGateway;
import com.security.api.domain.entity.UserEntityDomain;
import com.security.api.infra.persistence.entity.UserEntityJpa;
import com.security.api.infra.persistence.mapper.UserMapper;
import com.security.api.infra.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioGatewayImpl implements UsuarioGateway {
    private final UserRepository userRepository;

    public UsuarioGatewayImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserEntityDomain> findUserByLogin(String login) {
        Optional<UserEntityJpa> userEntityJpa = this.userRepository.findByLogin(login);
        return userEntityJpa.map(UserMapper::toModel);
    }

    @Override
    public boolean existsUserByLogin(String login) {
        return this.userRepository.existsByLogin(login);
    }

    @Override
    public void salvar(UserEntityDomain user) {
        this.userRepository.save(UserMapper.toEntity(user));
    }
}

package com.security.api.application.gateway;

import com.security.api.domain.entity.UserEntityDomain;

import java.util.Optional;

public interface UsuarioGateway {
    Optional<UserEntityDomain> findUserByLogin(String login);
    boolean existsUserByLogin(String login);
    void salvar(UserEntityDomain user);
}

package com.security.api.infra.persistence.mapper;

import com.security.api.domain.entity.UserEntityDomain;
import com.security.api.domain.enums.UserRole;
import com.security.api.infra.persistence.entity.UserEntityJpa;

public class UserMapper {
    public static UserEntityJpa toEntity(UserEntityDomain userDomain) {
        String role = userDomain.role();
        return new UserEntityJpa(null, userDomain.username(), userDomain.login(), userDomain.password(), UserRole.valueOf(role));
    }

    public static UserEntityDomain toModel(UserEntityJpa userJpa) {
        return new UserEntityDomain(userJpa.getUsername(), userJpa.getLogin(), userJpa.getPassword(), userJpa.getRole().name());
    }

}

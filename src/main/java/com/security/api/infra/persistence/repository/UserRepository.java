package com.security.api.infra.persistence.repository;

import com.security.api.infra.persistence.entity.UserEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntityJpa, UUID> {
     Optional<UserEntityJpa> findByLogin(String login);
     boolean existsByLogin(String login);
}

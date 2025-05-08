package com.security.api.application.gateway;

import com.security.api.domain.entity.UserEntityDomain;

public interface TokenEncoderAdapter {
    String gerar(UserEntityDomain usuario);
    String validar(String token);
}

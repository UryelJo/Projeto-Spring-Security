package com.security.api.application.usecases;

import com.security.api.domain.exception.custom.NotFoundException;
import com.security.api.domain.exception.custom.PasswordInvalidException;
import com.security.api.infra.http.request.user.LoginUserRequest;
import com.security.api.application.gateway.TokenEncoderAdapter;
import com.security.api.application.gateway.PasswordEncoderAdapter;
import com.security.api.application.gateway.UsuarioGateway;
import com.security.api.domain.entity.UserEntityDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RealizarLoginUC {
    private final UsuarioGateway usuarioGateway;
    private final TokenEncoderAdapter tokenEncoderAdapter;
    private final PasswordEncoderAdapter passwordEncoderAdapter;

    @Autowired
    public RealizarLoginUC(UsuarioGateway usuarioGateway,
                           TokenEncoderAdapter tokenEncoderAdapter,
                           PasswordEncoderAdapter passwordEncoderAdapter) {
        this.usuarioGateway = usuarioGateway;
        this.tokenEncoderAdapter = tokenEncoderAdapter;
        this.passwordEncoderAdapter = passwordEncoderAdapter;
    }

    @Transactional
    public String execute(LoginUserRequest request) {
        boolean usuarioEhExistente = this.usuarioGateway.existsUserByLogin(request.login());
        if(!usuarioEhExistente) {throw new NotFoundException("Usuário não cadastrado!!");}

        UserEntityDomain usuarioBuscado = this.usuarioGateway.findUserByLogin(request.login())
                .orElseThrow(NotFoundException::new);

        boolean senhaEhValida = this.passwordEncoderAdapter.compare(request.password(), usuarioBuscado.password());
        if(!senhaEhValida) {throw new PasswordInvalidException();}

        return this.tokenEncoderAdapter.gerar(usuarioBuscado);
    }
}

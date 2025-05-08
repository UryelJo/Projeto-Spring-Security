package com.security.api.application.usecases;

import com.security.api.application.gateway.PasswordEncoderAdapter;
import com.security.api.application.gateway.UsuarioGateway;
import com.security.api.domain.exception.custom.UserFoundException;
import com.security.api.infra.http.request.user.CreateUserRequest;
import com.security.api.domain.entity.UserEntityDomain;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrarUsuarioUC {
    private final UsuarioGateway usuarioGateway;
    private final PasswordEncoderAdapter passwordEncoderAdapter;

    @Autowired
    public RegistrarUsuarioUC(UsuarioGateway usuarioGateway,
                              PasswordEncoderAdapter passwordEncoderAdapter) {
        this.usuarioGateway = usuarioGateway;
        this.passwordEncoderAdapter = passwordEncoderAdapter;
    }

    @Transactional
    public void execute(CreateUserRequest request) {
       boolean usuarioEhExistente = this.usuarioGateway.existsUserByLogin(request.login());
       if (usuarioEhExistente) {throw new UserFoundException("Usuário já cadastrado!!");}

       String senhaCriptografada = this.passwordEncoderAdapter.encode(request.password());

       UserEntityDomain usuarioCadastrado = new UserEntityDomain(request.username(), request.login(), senhaCriptografada, request.roleUser());

       this.usuarioGateway.salvar(usuarioCadastrado);
    }
}

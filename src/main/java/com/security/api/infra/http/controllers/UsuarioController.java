package com.security.api.infra.http.controllers;

import com.security.api.application.usecases.RealizarLoginUC;
import com.security.api.application.usecases.RegistrarUsuarioUC;
import com.security.api.infra.http.dto.TokenResponseDTO;
import com.security.api.infra.http.request.user.CreateUserRequest;
import com.security.api.infra.http.request.user.LoginUserRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    private final RealizarLoginUC realizarLoginUC;
    private final RegistrarUsuarioUC registrarUsuarioUC;

    @Autowired
    public UsuarioController(RealizarLoginUC realizarLoginUC,
                             RegistrarUsuarioUC registrarUsuarioUC) {
        this.realizarLoginUC = realizarLoginUC;
        this.registrarUsuarioUC = registrarUsuarioUC;
    }

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> registrarUsuario(@RequestBody @Valid CreateUserRequest request) {
        this.registrarUsuarioUC.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> validarUsuario(@RequestBody @Valid LoginUserRequest request) {
        String token = this.realizarLoginUC.execute(request);
        return ResponseEntity.ok(new TokenResponseDTO(token));
    }
}

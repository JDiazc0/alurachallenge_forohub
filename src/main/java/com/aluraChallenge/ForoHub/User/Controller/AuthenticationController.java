package com.aluraChallenge.ForoHub.User.Controller;

import com.aluraChallenge.ForoHub.infra.security.AuthJwtDTO;
import com.aluraChallenge.ForoHub.User.DTO.AuthUserDTO;
import com.aluraChallenge.ForoHub.User.User;
import com.aluraChallenge.ForoHub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService){
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity authUser(@RequestBody @Valid AuthUserDTO authUserDTO){
        Authentication authToken = new UsernamePasswordAuthenticationToken(authUserDTO.email(),authUserDTO.password());
        authenticationManager.authenticate(authToken);
        var authUser = authenticationManager.authenticate(authToken);
        var JWToken = tokenService.generateToken((User) authUser.getPrincipal());
        return ResponseEntity.ok(new AuthJwtDTO(JWToken));
    }
}

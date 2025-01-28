package com.rezende.learn.controllers;

import com.rezende.learn.dtos.*;
import com.rezende.learn.entities.Role;
import com.rezende.learn.entities.User;
import com.rezende.learn.repositories.UserRepository;
import com.rezende.learn.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    /*
        Cria um objeto grerencido pelo spring com as credencias do usuario
        Verifica as credenciais são validas junto ao banco de dados
        caso seja invalida, o euthenticationManager lançará uma execção BadCredentialsException
        se não, gera um token de acesso
        retorna um status ok com as info de login.
    */
    @PostMapping("/login")
    public ResponseEntity<LoginTokenDTO> login(@Valid @RequestBody AuthenticationDTO dto) {
        try {
            Authentication usernamePassword = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
            Authentication authentication = authenticationManager.authenticate(usernamePassword);
            String token = tokenService.generateToken((User) authentication.getPrincipal());
            return ResponseEntity.ok(new LoginTokenDTO(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    /*
        Recebe como parâmetro um formulario com informações do usuario e login
        Verifica se o usuario não existe, se existir, retorna um badRequest.
        Se não, criptografa a senha e copia os dados recebidos para uma entidade
        Salva a entidade no banco e retorna um status ok com o novo usuario registrado.
    */
    @PostMapping("/register")
    public ResponseEntity<RegisterDTO> register(@Valid @RequestBody RegisterDTO dto) {
        if (userRepository.findByUsername(dto.getEmail()) != null) {
            return ResponseEntity.badRequest().build();
        }
        String passwordEncoder = new BCryptPasswordEncoder(12).encode(dto.getPassword());
        User user = new User();
        user.setId(dto.getId());
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setPassword(passwordEncoder);
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setBirthDate(dto.getBirthDate());
        for (RoleDTO role : dto.getRoles()) {
            user.getRoles().add(new Role(role.getId(), role.getAuthority()));
        }
        user = userRepository.save(user);
        return ResponseEntity.ok(new RegisterDTO(user));
    }

}

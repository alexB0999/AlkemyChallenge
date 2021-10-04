package com.project.DisneyApi.controller;

import com.project.DisneyApi.dto.DTORegister;
import com.project.DisneyApi.dto.Mensaje;
import com.project.DisneyApi.entity.Usuario;
import com.project.DisneyApi.security.AuthenticationRequest;
import com.project.DisneyApi.security.AuthenticationResponse;
import com.project.DisneyApi.security.JwtService;
import com.project.DisneyApi.security.MyUserDetailService;
import com.project.DisneyApi.service.EmailService;
import com.project.DisneyApi.serviceImpl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class UsuarioController {

    private final AuthenticationManager authenticationManager;
    private final MyUserDetailService myUserDetailService;
    private final JwtService jwtService;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private EmailService emailService;

    public UsuarioController(AuthenticationManager authenticationManager,
                             MyUserDetailService myUserDetailService, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.myUserDetailService = myUserDetailService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody DTORegister dtoRegister) throws IOException {
        if (dtoRegister.getUsername().equals(null)){
            return new ResponseEntity(new Mensaje("\"El campo usuario es requerido\""),HttpStatus.BAD_REQUEST);
        }
        if (dtoRegister.getEmail().equals(null)){
            return new ResponseEntity(new Mensaje("El campo email es requerido"), HttpStatus.BAD_REQUEST);
        }
        if ((dtoRegister.getPassword().equals(null))){
            return new ResponseEntity(new Mensaje("El campo contraseña es requerido"), HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = new Usuario();
        usuario.setUsername(dtoRegister.getUsername());
        usuario.setEmail(dtoRegister.getEmail());
        usuario.setPassword(dtoRegister.getPassword());
        usuario.setRole(dtoRegister.getRole());

        usuarioService.registrar(usuario);
        emailService.setDestinatario(usuario.getEmail());
        emailService.sendWelcomeEmail();
        return new ResponseEntity(new Mensaje("Usuario registrado exitosamente"), HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid username or password", e);
        }
        UserDetails userDetails = myUserDetailService.loadUserByUsername(authenticationRequest.getUsername());
        String token = jwtService.createToken(userDetails);
        return new AuthenticationResponse(token);
    }

}



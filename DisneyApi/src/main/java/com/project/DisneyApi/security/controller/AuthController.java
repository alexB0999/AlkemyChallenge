package com.project.DisneyApi.security.controller;

import com.project.DisneyApi.dto.Mensaje;
import com.project.DisneyApi.security.dto.JwtDTO;
import com.project.DisneyApi.security.dto.LoginUsuario;
import com.project.DisneyApi.security.dto.NuevoUsuario;
import com.project.DisneyApi.security.entity.Rol;
import com.project.DisneyApi.security.entity.Usuario;
import com.project.DisneyApi.security.enums.RolNombre;
import com.project.DisneyApi.security.jwt.JwtProvider;
import com.project.DisneyApi.security.service.RolService;
import com.project.DisneyApi.security.service.UsuarioService;
import com.project.DisneyApi.sendgrid.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    private EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<?> signin(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) throws IOException {
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos o email inválidos"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByUsername(nuevoUsuario.getUsername()))
            return new ResponseEntity(new Mensaje("Nombre de Usuario ya está registrado"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("Correo ya está registrado"), HttpStatus.BAD_REQUEST);
        Usuario usuario = Usuario.builder().nombre(nuevoUsuario.getNombre()).username(nuevoUsuario.getUsername())
                .email(nuevoUsuario.getEmail()).password(passwordEncoder.encode(nuevoUsuario.getPassword())).build();
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        emailService.setDestinatario(usuario.getEmail());
        emailService.sendWelcomeEmail();
        return new ResponseEntity(new Mensaje("Usuario creado satisfactoriamente"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos inválidos"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                        (loginUsuario.getUsername(),loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        JwtDTO jwtDTO = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDTO, HttpStatus.OK);
    }
}

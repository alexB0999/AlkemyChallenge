package com.project.DisneyApi.emailSender.controller;

import com.project.DisneyApi.dto.Mensaje;
import com.project.DisneyApi.emailSender.dto.ChangePasswordDTO;
import com.project.DisneyApi.emailSender.dto.EmailValuesDTO;
import com.project.DisneyApi.emailSender.service.EmailService;
import com.project.DisneyApi.security.entity.Usuario;
import com.project.DisneyApi.security.service.UsuarioService;
import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email-password")
@CrossOrigin
public class EmailController {

  @Autowired
  EmailService emailService;

  @Autowired
  UsuarioService usuarioService;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Value("${spring.mail.username}")
  private String mailFrom;

  @Value("${mail.subject}")
  private String subject;

  @PostMapping("/send-email")
  public ResponseEntity<?> sendEmail(@RequestBody EmailValuesDTO valuesDTO){
    Optional<Usuario> usuarioOptional = usuarioService.getByUsernameOrEmail(valuesDTO.getMailTo());
    if(!usuarioOptional.isPresent()){
      return new ResponseEntity<>(new Mensaje("No existe ningún usuario con esas credenciales"),
          HttpStatus.NOT_FOUND);
    }

    Usuario usuario = usuarioOptional.get();
    valuesDTO.setMailFrom(mailFrom);
    valuesDTO.setMailTo(usuario.getEmail());
    valuesDTO.setSubject(subject);
    valuesDTO.setUsername(usuario.getUsername());

    UUID uuid = UUID.randomUUID();
    String tokenPassword = uuid.toString();

    valuesDTO.setTokenPassword(tokenPassword);
    usuario.setTokenPassword(tokenPassword);

    usuarioService.save(usuario);
    emailService.sendEmail(valuesDTO);

    return new ResponseEntity<>(new Mensaje("Te hemos enviado un correo"), HttpStatus.OK);
  }

  @PostMapping("/change-password")
  public ResponseEntity<?> changePassword(@Valid@RequestBody ChangePasswordDTO changePasswordDTO,
      BindingResult bindingResult){
    if(bindingResult.hasErrors()){
      return new ResponseEntity<>(new Mensaje("Campos mal puestos"), HttpStatus.BAD_REQUEST);
    }
    if(!changePasswordDTO.getPassword().equals(changePasswordDTO.getConfirmPassword())){
      return new ResponseEntity<>(new Mensaje("Las contraseñas no coinciden"), HttpStatus.BAD_REQUEST);
    }
    Optional<Usuario>usuarioOptional = usuarioService.findByTokenPassword(changePasswordDTO.getTokenPassword());
    if(!usuarioOptional.isPresent()){
      return new ResponseEntity<>(new Mensaje("No existe ningún usuario con esas credenciales"),
          HttpStatus.NOT_FOUND);
    }
    Usuario usuario = usuarioOptional.get();
    String newPassword = passwordEncoder.encode(changePasswordDTO.getPassword());
    usuario.setPassword(newPassword);
    usuario.setTokenPassword(null);
    usuarioService.save(usuario);
    return new ResponseEntity<>(new Mensaje("Contraseña actualizada"), HttpStatus.OK);
  }

}

package com.project.DisneyApi.controller;

import com.project.DisneyApi.entity.Usuario;
import com.project.DisneyApi.serviceImpl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @GetMapping("/auth/login")
    public String login(Model model){
        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    @GetMapping("/auth/registro")
    public String registroForm(Model model){
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/auth/registro")
    public String registro(@Valid @ModelAttribute Usuario usuario, BindingResult result, Model model){

        if(result.hasErrors()){
            return "redirect:/auth/registro";
        }else{
            model.addAttribute("usuario", usuarioService.registrar(usuario));
        }
        return "redirect:/auth/login";
    }
}

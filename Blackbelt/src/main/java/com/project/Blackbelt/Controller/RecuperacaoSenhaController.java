package com.project.Blackbelt.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RecuperacaoSenhaController {

    @GetMapping("/recuperar-senha")
    public String mostrarFormularioRecuperarSenha() {
        return "recuperar-senha";
    }

   @PostMapping("/recuperar-senha")
public String processarFormularioRecuperarSenha(@RequestParam("email") String email, Model model) {
    String token = "123";
    System.out.println("Email recebido: " + email);
    return "redirect:/redefinir-senha?token=" + token;
}

    @GetMapping("/redefinir-senha")
    public String mostrarFormularioRedefinirSenha(@RequestParam("token") String token, Model model) {
        model.addAttribute("token", token);
        return "redefinir-senha";
    }

    @PostMapping("/redefinir-senha")
    public String processarFormularioRedefinirSenha(
            @RequestParam("novaSenha") String novaSenha,
            @RequestParam("confirmarSenha") String confirmarSenha,
            @RequestParam("token") String token,
            Model model
    ) {
        if (!novaSenha.equals(confirmarSenha)) {
            model.addAttribute("erro", "As senhas não coincidem.");
            model.addAttribute("token", token);
            return "redefinir-senha";
        }

        // Aqui você atualizaria a senha no banco usando o token
        model.addAttribute("success", "Senha redefinida com sucesso.");
        return "login";
    }
}

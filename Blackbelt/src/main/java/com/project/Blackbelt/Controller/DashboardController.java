package com.project.Blackbelt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.Blackbelt.Model.Users;

import com.project.Blackbelt.Repository.UserRepository;

@Controller
public class DashboardController {

    @Autowired
    private UserRepository userRepository;

 

    @RequestMapping("/")
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Users usua = userRepository.findByUsername(username);
    
        // Passar o nome formatado do usuário
        if (username != null) {
            String nomeFormatado = formatarNome(username);
            model.addAttribute("message", nomeFormatado);
        }
    
     
    
        return "dashboard";
    }
    
    private String formatarNome(String username) {
        // Formata o nome para exibição (exemplo: "ErysonMoreira" -> "Eryson Moreira")
        return username.replaceAll("([a-z])([A-Z])", "$1 $2");
    }
}

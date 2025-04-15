package com.project.Blackbelt.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.Blackbelt.Model.Empresa;
import com.project.Blackbelt.Repository.EmpresaRepository;


@Controller
public class EmpresaController {
	
	@Autowired
	private EmpresaRepository filialrepository;
	
	@RequestMapping("/empresa")
	public String filial(Model model) {	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();


		if(username != null){
			String nomeFormatado = formatarNome(username);
			model.addAttribute("message", nomeFormatado);
		}
		model.addAttribute("filialobj", new Empresa());
		Iterable<Empresa> filiais = filialrepository.findAll();
        model.addAttribute("filiais", filiais);
		return "/paginas/gestaodeempresas";
	}
	
	private String formatarNome(String username) {
		// Divide o nome em letras maiúsculas e minúsculas
		// Exemplo: "ErysonMoreira" -> "Eryson Moreira"
		return username.replaceAll("([a-z])([A-Z])", "$1 $2");
	}
	
	
	 @RequestMapping(method = RequestMethod.POST, value = "/salvarfiliais")
	    public ModelAndView salvar(@ModelAttribute Empresa filiais) {
	        ModelAndView modelview = new ModelAndView("redirect:/empresa");
	        filialrepository.save(filiais);
	        return modelview;
	    }

	 @RequestMapping(method = RequestMethod.POST, value = "/editarfilial")
	 public ModelAndView editarFilial(@ModelAttribute Empresa filial) {
	     // Busca a filial pelo CNPJ
	     Optional<Empresa> filialExistente = filialrepository.findById(filial.getCnpj());
	     
	     if (filialExistente.isPresent()) {
	         // Atualiza os dados da filial existente
	         Empresa existente = filialExistente.get();
	         existente.setNome(filial.getNome());
	         existente.setRazaosocial(filial.getRazaosocial());
	         
	         // Salva as alterações no banco
	         filialrepository.save(existente);
	     } else {
	         System.out.println("Filial não encontrada com o CNPJ: " + filial.getCnpj());
	     }
	     
	     // Redireciona para a página de gestão de filiais
	     return new ModelAndView("redirect:/empresa");
	 }

	    @RequestMapping(method = RequestMethod.GET, value = "/removerfilial/{cnpjfilial}")
	    public ModelAndView excluir(@PathVariable("cnpjfilial") String cnpj) {
	        filialrepository.deleteById(cnpj);
	        return new ModelAndView("redirect:/empresa");
	    }
	

}

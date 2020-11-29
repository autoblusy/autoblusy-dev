package com.spring.WEB2.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties.View;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.WEB2.model.Usuario;
import com.spring.WEB2.service.UsuarioService;

@Controller
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping( value = "/usuarios", method = RequestMethod.GET)
	public ModelAndView getUsuarios() {
		ModelAndView mv = new ModelAndView( "usuarios");
		List<Usuario> usuarios = usuarioService.findAll();
		System.out.println(usuarioService.findByCpf(3123).getUsuarioPerfil().getNome());
		mv.addObject("usuarios",usuarios);
		System.out.println(usuarios.get(0).getAdmin());
		return mv;
	}
	
	  @RequestMapping(value="/usuarios/novo", method = RequestMethod.GET)
	    public ModelAndView getUsuarioForm(){
		  ModelAndView mv = new ModelAndView( "usuarioform");
		  
		  return mv;
	    }

	    @RequestMapping(value="/usuarios/novo", method=RequestMethod.POST)
	    public String savePost( Usuario usuario, BindingResult result, RedirectAttributes attributes){
	        if(result.hasErrors()){
	            attributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram preenchidos!");
	            return "redirect:/usuarios/novo";
	        }
	        usuarioService.save(usuario);
	        return "redirect:/usuarios";
	    }
}

package com.spring.WEB2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.WEB2.model.Usuario;
import com.spring.WEB2.repository.UsuarioRepository;

@Controller
public class IndexController {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@RequestMapping( value="/", method = RequestMethod.GET)
	public ModelAndView getHome(HttpSession session) {
		System.out.println("Alguma coisa");
//		session.setAttribute("usuario", usuarioRepository.findByCpf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String nome;    

		if (principal instanceof UserDetails) {
		    nome = ((UserDetails)principal).getUsername();
		} else {
		    nome = principal.toString();
		}
		System.out.println(nome+"Alguma coisa");
		Usuario usuario = usuarioRepository.findByCpf(Long.parseLong(nome));
		System.out.println(usuario.getCpf());
		session.setAttribute("usuario", usuario);
		return new ModelAndView("redirect:/dashboard");
	}
}

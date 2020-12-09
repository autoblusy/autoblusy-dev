package com.spring.WEB2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.WEB2.model.Usuario;
import com.spring.WEB2.repository.UsuarioRepository;
import com.spring.WEB2.utils.StringUtils;

@Controller
public class AuthenticateController {
	
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	 @GetMapping("/login")
	    public String login() {
	        return "login";
	    }
	 
	 
	 @GetMapping("/forgot")
	    public ModelAndView forgot(  Usuario usuario) {
	        ModelAndView mv = new ModelAndView("forgot");
	        mv.addObject(usuario);
			return mv;
	    }
	 
	 @PostMapping("/forgot")
	    public ModelAndView forgot(String username, String palavrachave1, String palavrachave2, RedirectAttributes attr) {
		 	System.out.println(username);
	        Usuario user = usuarioRepository.findByCpf( Long.parseLong(username) );
	        boolean bol = StringUtils.compareForgotPasswords( palavrachave1, palavrachave2, user.getUsuarioPerfil().getSenha_recuperacao1(), user.getUsuarioPerfil().getSenha_recuperacao2() );
	        System.out.println(bol);
	        System.out.println(user.getCpf());
	        if(!bol) {
	        	return (  new  ModelAndView("forgot").addObject("usuario",user).addObject("error","Senhas de alteração inválidas"));
	        }  else {
	        		attr.addFlashAttribute("usuario",user);
				 return new ModelAndView("redirect:/newpassword");
	        }
	    }
	 
	 @GetMapping("/forgot/{username}")
	 	public ModelAndView forgot(@PathVariable(value="username") Long username,RedirectAttributes attr) {
		 Usuario usuario = usuarioRepository.findByCpf(username);

		 if(usuario == null) {
			 ModelAndView mv = new ModelAndView();
			 mv.addObject("error","cpf não definido");
			 mv.setViewName("login");
			 return mv;
		 } else {
			 attr.addFlashAttribute("usuario",usuario);
			 System.out.println(attr);
			 return new ModelAndView("redirect:/forgot");
		 }
	 }
	 
	 
	 @GetMapping("/newpassword")
	    public ModelAndView newPassword( Usuario usuario) {
		 
		 	System.out.println(usuario.getCpf()	);
	        ModelAndView mv = new ModelAndView("newpassword");

			return mv;
	    }
	 
	 @PostMapping("/newpassword")
	    public ModelAndView newPassword(String username,String novasenha1, String novasenha2) {
		 
		 	if(novasenha1.equalsIgnoreCase(novasenha2)) {
		 		String encodedPassword = StringUtils.createPasswordWithSecurity(novasenha1);
		 		Usuario usuario = usuarioRepository.findByCpf(Long.parseLong(username));
		 		usuario.setSenha(encodedPassword);
		 		usuarioRepository.save(usuario);
		 	}  else {
		 		
		 	}
		 	return new ModelAndView("redirect:/login");
	    }
	 
	// finaliza sessão
	 @RequestMapping("logout")
	 public String logout(HttpSession session) {
	 	session.invalidate();
	 	return "redirect:/successLogout";
	 }
	 
}

package com.spring.WEB2.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.WEB2.utils.FileUtils;

import com.spring.WEB2.model.Usuario;
import com.spring.WEB2.model.UsuarioPerfil;
import com.spring.WEB2.repository.UsuarioRepository;
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
	    public ModelAndView getUsuarioForm( HttpSession session){
		  System.out.println((Usuario)session.getAttribute("usuario")+"alguma coisa");
		  ModelAndView mv = new ModelAndView( "usuarioform");
		  Usuario usuario = (Usuario)session.getAttribute("usuario");
		  mv.addObject("usuario",usuario);
		  mv.addObject("perfilUsuario",usuario.getUsuarioPerfil());
		  return mv;
	    }

	    @RequestMapping(value="/usuarios/novo", method=RequestMethod.POST)
	    public String saveUsuario( Usuario usuario, UsuarioPerfil usuarioPerfil, MultipartFile photo, RedirectAttributes attributes){
	    	usuarioPerfil.setFotoUsuario("ft-"+usuario.getCpf());
	    	usuarioPerfil.getCorUsuario();
	    	usuarioService.verifyIfExistsAndSave(usuario.getCpf(), usuario, usuarioPerfil);
	    	FileUtils fu = new FileUtils();
	    	fu.singleFileUpload(photo, usuarioPerfil.getFotoUsuario());
	        return "redirect:/";
	    }
	    
	    
		@RequestMapping(value="/dashboard", method = RequestMethod.GET)
	    public ModelAndView Dashboard(HttpSession session){
			Usuario user = (Usuario) session.getAttribute("usuario");
			ModelAndView mv;
			if( user.getAdmin()) {
				mv = new ModelAndView("dashboardAdmin");
			} else {
				mv = new ModelAndView("dashboardUsuario");
			}
			mv.addObject("usuario", user);
			mv.addObject("perfilUsuario", user.getUsuarioPerfil());
			return mv;
			}
		
		@RequestMapping(value="/successLogout", method = RequestMethod.GET)
	    public ModelAndView logoutSuccess(RedirectAttributes attributes){
			return new ModelAndView("logoutSuccess");
		}
		
		@RequestMapping(value="/usuario/alterar", method = RequestMethod.GET)
	    public ModelAndView alterarPerfil( HttpSession session){
		  ModelAndView mv = new ModelAndView( "alterarperfil");
		  Usuario usuario = (Usuario)session.getAttribute("usuario");
		  mv.addObject("usuario",usuario);
		  mv.addObject("perfilUsuario",usuario.getUsuarioPerfil());
		  return mv;
	    }
		
		@RequestMapping(value="/usuario/alterar", method=RequestMethod.POST)
	    public String alterarPerfilPost( Usuario usuario, UsuarioPerfil usuarioPerfil, MultipartFile photo){
	    	usuarioPerfil.setFotoUsuario("ft-"+usuario.getCpf());
	    	usuarioPerfil.getCorUsuario();
	    	usuarioService.verifyIfExistsAndSave(usuario.getCpf(), usuario, usuarioPerfil);
	    	FileUtils fu = new FileUtils();
	    	fu.singleFileUpload(photo, usuarioPerfil.getFotoUsuario());
	        return "redirect:/";
	    }
	    
		@RequestMapping(value="/usuario/mostrar", method = RequestMethod.GET)
	    public ModelAndView mostrarPerfil( HttpSession session){
		  ModelAndView mv = new ModelAndView( "mostrarPerfil");
		  Usuario usuario = (Usuario)session.getAttribute("usuario");
		  mv.addObject("usuario",usuario);
		  mv.addObject("perfilUsuario",usuario.getUsuarioPerfil());
		  return mv;
	    }
	    
}
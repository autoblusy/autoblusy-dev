package com.spring.WEB2.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.WEB2.model.Usuario;
import com.spring.WEB2.model.UsuarioPerfil;
import com.spring.WEB2.repository.UsuarioPerfilRepository;
import com.spring.WEB2.repository.UsuarioRepository;
import com.spring.WEB2.service.UsuarioService;
import com.spring.WEB2.utils.FileUtils;

@Controller
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	UsuarioPerfilRepository usuarioPerfilRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	
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
		  Usuario usuario = (Usuario)session.getAttribute("usuario");
		  if( usuario.getAdmin()) {
			  System.out.println((Usuario)session.getAttribute("usuario")+"alguma coisa");
			  ModelAndView mv = new ModelAndView( "usuarioform");
			 
			  mv.addObject("usuario",usuario);
			  mv.addObject("perfilUsuario",usuario.getUsuarioPerfil());
			  return mv;
			} else {
				return new ModelAndView("redirect:/");
			}
	    }

	    @RequestMapping(value="/usuarios/novo", method=RequestMethod.POST)
	    public String saveUsuario( Usuario usuario, UsuarioPerfil usuarioPerfil, MultipartFile photo, RedirectAttributes attributes){
	    	usuarioPerfil.setFotoUsuario("ft-"+usuario.getCpf());
	    	usuarioPerfil.getCorUsuario();
	    	System.out.println(usuario.getAdmin());
	    	usuarioService.verifyIfExistsAndSave(usuario.getCpf(), usuario, usuarioPerfil, false);
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
	    public String alterarPerfilPost( Usuario usuario, UsuarioPerfil usuarioPerfil, HttpSession session){
			 Usuario userSession = (Usuario)session.getAttribute("usuario");
			if(usuario.getSenha() != null) {
				System.out.println(usuario.getSenha());
				userSession.setSenha(usuario.getSenha());
				usuarioService.save(userSession);
			}
	    	usuarioPerfil.setId(userSession.getUsuarioPerfil().getId());
	    	usuarioPerfil.setNome(userSession.getUsuarioPerfil().getNome());
	    	usuarioPerfil.setUltimoAcesso(userSession.getUsuarioPerfil().getUltimoAcesso(true));
	    	usuarioPerfil.setFotoUsuario(userSession.getUsuarioPerfil().getFotoUsuario());
	    	usuarioPerfil.setUltimoAcesso((new Date(System.currentTimeMillis())).toString());
	    	usuarioPerfilRepository.save(usuarioPerfil);
	    
	        return "redirect:/";
	    }
		
		@RequestMapping(value="/usuarios/editar/{id}", method=RequestMethod.GET)
	    public ModelAndView editarUsuario(  @PathVariable("id") long id ,HttpSession session){
			 Usuario userSession = (Usuario)session.getAttribute("usuario");
			 Optional<Usuario> optional =  usuarioRepository.findById(id);
			 Usuario userEdit = optional.get();
			 if( userSession.getAdmin()) {
				  System.out.println((Usuario)session.getAttribute("usuario")+"alguma coisa");
				  ModelAndView mv = new ModelAndView( "editusuario");
				 
				  mv.addObject("usuario",userEdit);
				  mv.addObject("perfilUsuario",userSession.getUsuarioPerfil());
				  return mv;
				} else {
					return new ModelAndView("redirect:/");
				}
	    }
		
		@RequestMapping(value="/usuarios/editar", method=RequestMethod.POST)
	    public ModelAndView editarUsuario( Usuario usuario,long perfilId, UsuarioPerfil usuarioPerfil, HttpSession session){
			 Usuario userSession = (Usuario)session.getAttribute("usuario");
			 UsuarioPerfil perfil = usuarioPerfilRepository.findById(perfilId).get();
			 perfil.setNome(usuarioPerfil.getNome());
			 usuario.setUsuarioPerfil(perfil);
			 usuarioRepository.saveAndFlush(usuario);
			 if(usuario.getId() == userSession.getId()) {
				 return new ModelAndView("redirect:/logout");
			 }
			 return new ModelAndView("redirect:/administracao");
				
	    }
		
		@RequestMapping(value="/usuarios/remover/{id}", method=RequestMethod.GET)
	    public ModelAndView removerUsuario(  @PathVariable("id") long id ,HttpSession session){
			 Usuario userSession = (Usuario)session.getAttribute("usuario");
			 Optional<Usuario> optional =  usuarioRepository.findById(id);
			 Usuario userEdit = optional.get();
			 if( userSession.getAdmin() && !(userSession.getId() == id)) {
				 usuarioRepository.delete(userEdit);
				}
				return new ModelAndView("redirect:/administracao");
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
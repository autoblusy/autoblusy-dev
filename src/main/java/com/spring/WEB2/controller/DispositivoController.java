package com.spring.WEB2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.WEB2.model.Dispositivo;
import com.spring.WEB2.service.DispositivoService;

@Controller
public class DispositivoController {
	
	@Autowired
	DispositivoService dispositivoService;
	
	@RequestMapping( value = "/dispositivos", method = RequestMethod.GET)
	public ModelAndView getDispositivos() {
		ModelAndView mv = new ModelAndView( "dispositivos");
		List<Dispositivo> dispositivos = dispositivoService.findAll();
		mv.addObject( "dispositivos", dispositivos);
		return mv;
	}
	
	@RequestMapping( value =  "/dispositivos/{id}", method = RequestMethod.GET)
	public ModelAndView getDispositivo( @PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView( "dispositivodetalhes");
		Dispositivo dispositivo = dispositivoService.findById(id);
		mv.addObject("dispositivo", dispositivo);
		return mv;
	}
}

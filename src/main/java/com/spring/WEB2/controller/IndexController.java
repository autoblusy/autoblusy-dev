package com.spring.WEB2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	
	@RequestMapping( value="/", method = RequestMethod.GET)
	public ModelAndView getHome() {
		return new ModelAndView("redirect:/login");
	}
	 @GetMapping("/login")
	    public String login() {
	        return "login";
	    }
}

package com.ultra.fitness.nutritions.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	
	@RequestMapping(value = "/")
	public String user() {
		
	    return "index";
	}
	
	@RequestMapping("/user/{id}")
	public String developer(@PathVariable Long id) {
		return "product-list.html";
	}
	
	private void generateException(){
	    throw new IndexOutOfBoundsException();      
	}

	private ModelAndView handleException(){
	     return new ModelAndView("error.jsp");
	}

}

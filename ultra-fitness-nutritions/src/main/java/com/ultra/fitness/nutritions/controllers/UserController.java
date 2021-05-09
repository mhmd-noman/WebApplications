package com.ultra.fitness.nutritions.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	@RequestMapping(value = "/")
	public String user() {
		return "index.html";
	}
	
	@RequestMapping("/user/{id}")
	public String developer(@PathVariable Long id) {
		return "product-list.html";
	}

}

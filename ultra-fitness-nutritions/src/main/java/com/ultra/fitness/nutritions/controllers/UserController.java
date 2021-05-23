package com.ultra.fitness.nutritions.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ultra.fitness.nutritions.service.UserService;

import common.beans.User;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	public UserController(UserService userService) {
		super();
		this.userService=userService;
	}

	@ModelAttribute("user")
	public User user() {
		return new User();
	}
	
	@GetMapping
	public String showLoginPage() {
		return "login";
	}
	
	@PostMapping
	public String userSignUp(@ModelAttribute("user") User user) {
		String response = userService.userSignUp(user);
		if ("100".equalsIgnoreCase(response)) {
			return "redirect:/user?success";
		}
		return "redirect:/user?fail";
	}
}

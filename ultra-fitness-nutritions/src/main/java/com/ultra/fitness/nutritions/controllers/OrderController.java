package com.ultra.fitness.nutritions.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/order")
public class OrderController {
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@RequestMapping(value="/checkout", method = RequestMethod.GET)
	public String getProducts(Model model) {
		return "checkout";
	}
}
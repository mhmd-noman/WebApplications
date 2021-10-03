package com.ultra.fitness.nutritions.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/cart")
public class CartController {
	private static final Logger logger = LoggerFactory.getLogger(CartController.class);

	@RequestMapping(value="/show-cart", method = RequestMethod.GET)
	public String getProducts(Model model) {
		logger.info(logger.isInfoEnabled() ? "ladning to cart": null);
		return "cart";
	}
	
	
}
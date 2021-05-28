package com.ultra.fitness.nutritions.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ultra.fitness.nutritions.service.ProductService;
import com.ultra.fitness.nutritions.utils.Utilities;

import common.beans.Product;
import common.request.MainRequestObject;
import common.response.MainResponseObject;

@Controller
@RequestMapping("/product")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	private MainResponseObject response;
	private MainRequestObject request;

	@RequestMapping(value="/getProducts", method = RequestMethod.GET)
	public String getProducts(Model model) {
		request = new MainRequestObject();
		request.setProductInfo(new Product());
		logger.info(logger.isInfoEnabled() ? "Going to get products from Product controller ...": null);
		response = productService.getProducts(request);
		logger.info(logger.isInfoEnabled() ? "Response Received: [ " +response.getResponseCode()+ " ][" +response.getResponseDesc()+ "]": null);
		if (!Utilities.isNullOrEmptyCollection(response.getProducts())) {
			model.addAttribute("products", response.getProducts());
		}
		return "product-list";
	}
}
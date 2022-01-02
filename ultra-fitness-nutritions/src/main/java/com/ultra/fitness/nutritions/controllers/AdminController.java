package com.ultra.fitness.nutritions.controllers;

import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ultra.fitness.nutritions.service.ProductService;
import com.ultra.fitness.nutritions.utils.Constants;
import com.ultra.fitness.nutritions.utils.Utilities;

import common.beans.Product;
import common.request.MainRequestObject;
import common.response.MainResponseObject;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private ProductService productService;
	private MainResponseObject response;
	private MainRequestObject request;
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@GetMapping("/home")
	public String adminHome() {
		return "admin-home";
	}
	
	@RequestMapping(value = "/manage-products")
	public String manageProducts(Model model) {
		request = new MainRequestObject();
		request.setProductInfo(new Product());
		request.setPageNo(Constants.DEFAULT_PAGE_NO);
		request.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		request.setReturnCount(true);
		logger.info(logger.isInfoEnabled() ? "Going to get products from Product controller ...": null);
		response = productService.getProducts(request);
		logger.info(logger.isInfoEnabled() ? "Response Received: [ " +response.getResponseCode()+ " ][" +response.getResponseDesc()+ "]": null);
		model.addAttribute("currentPage", Constants.DEFAULT_PAGE_NO);
		if (!Utilities.isNullOrEmptyCollection(response.getProducts())) {
			model.addAttribute("totalPages", java.lang.Math.ceil(response.getProducts().size()/Constants.DEFAULT_PAGE_SIZE + 1));
			model.addAttribute("products", response.getProducts());
		} else if (!Utilities.validateIfNullOrInvalidInteger(response.getTransCount())) {
			model.addAttribute("totalPages", response.getTransCount()/Constants.DEFAULT_PAGE_SIZE + 1);
			model.addAttribute("products", new ArrayList<Product>());
		} else {
			model.addAttribute("totalPages", Constants.ZERO);
			model.addAttribute("products", new ArrayList<Product>());
		}
		return "manage-products";
	}
	
	@GetMapping(value = "/manage-products/add-product")
	public String getAddProducts(Model model) {
		request = new MainRequestObject();
		request.setProductInfo(new Product());
		request.setPageNo(Constants.DEFAULT_PAGE_NO);
		request.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		request.setReturnCount(true);
		logger.info(logger.isInfoEnabled() ? "Going to get products from Product controller ...": null);
		
		model.addAttribute("product", new Product());
		return "add-product";
	}
	
	@PostMapping(value = "/manage-products/add-product")
	public String postAddProducts(@ModelAttribute("product") Product product, Model model, HttpServletRequest httpRequest) throws ParseException {
		request = new MainRequestObject();
		request.setProductInfo(product);
		product.setMfgDate(Utilities.formatDate(httpRequest.getParameter("mfgDatee"), Constants.DATE_FORMAT));
		product.setExpiryDate(Utilities.formatDate(httpRequest.getParameter("expiryDatee"), Constants.DATE_FORMAT));
		logger.info(logger.isInfoEnabled() ? "Going to add product from Product controller ...": null);
		productService.addProduct(request);
		return "redirect:/admin/manage-products";
	}
}

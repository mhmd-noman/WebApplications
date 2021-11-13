package com.ultra.fitness.nutritions.controllers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ultra.fitness.nutritions.service.ProductService;
import com.ultra.fitness.nutritions.utils.Constants;
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

	@RequestMapping(value="/get-products", method = RequestMethod.GET)
	public String getProducts(Model model) {
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
		return "product-list";
	}
	
	@RequestMapping(value="/get-requested-products", method = RequestMethod.GET)
	public String getProducts(Model model,  @RequestParam String productName) {
		request = new MainRequestObject();
		request.setProductInfo(new Product());
		request.setPageNo(Constants.DEFAULT_PAGE_NO);
		request.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		request.setReturnCount(true);
		request.getProductInfo().setName(productName);
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
		return "product-list";
	}

	@RequestMapping(value="/get-products-with-page", method = RequestMethod.GET)
	public String getProductDetail(@RequestParam Integer page, Model model) {
		request = new MainRequestObject();
		Product product = new Product();
		request.setPageNo(page);
		request.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		request.setProductInfo(product);
		request.setReturnCount(true);
		logger.info(logger.isInfoEnabled() ? "Going to get products for pageNo[" +page+ "] from Product controller ...": null);
		response = productService.getProducts(request);
		logger.info(logger.isInfoEnabled() ? "Response Received: [ " +response.getResponseCode()+ " ][" +response.getResponseDesc()+ "]": null);
		model.addAttribute("currentPage", page);
		if (!Utilities.isNullOrEmptyCollection(response.getProducts())) {
			model.addAttribute("totalPages", response.getTransCount()/Constants.DEFAULT_PAGE_SIZE + 1);
			model.addAttribute("products", response.getProducts());
		} else if (!Utilities.validateIfNullOrInvalidInteger(response.getTransCount())) {
			model.addAttribute("totalPages", response.getTransCount()/Constants.DEFAULT_PAGE_SIZE + 1);
			model.addAttribute("products", new ArrayList<Product>());
		} else {
			model.addAttribute("totalPages", Constants.ZERO);
			model.addAttribute("products", new ArrayList<Product>());
		}
		return "product-list";
	}
	
	@RequestMapping(value="/get-product-detail", method = RequestMethod.GET)
	public String getProductDetail(@RequestParam Integer id, @RequestParam String name, Model model) {
		request = new MainRequestObject();
		Product product = new Product();
		if (id == null || id < 1) {
			id = 1;
		}
		product.setId(id);
		request.setProductInfo(product);
		logger.info(logger.isInfoEnabled() ? "Going to get product detail for product[" +id+ "] from Product controller ...": null);
		response = productService.getProducts(request);
		logger.info(logger.isInfoEnabled() ? "Response Received: [ " +response.getResponseCode()+ " ][" +response.getResponseDesc()+ "]": null);
		if (!Utilities.isNullOrEmptyCollection(response.getProducts())) {
			model.addAttribute("product", response.getProducts().get(0));
		}
		return "product-detail";
	}
	
	@RequestMapping(value="/get-products-by-price-range", method = RequestMethod.GET)
	public String getProductsByPriceRange(Model model, @RequestParam Double minPrice, @RequestParam Double maxPrice) {
		request = new MainRequestObject();
		request.setProductInfo(new Product());
		request.setPageNo(Constants.DEFAULT_PAGE_NO);
		request.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		request.setReturnCount(true);
		
		model.getAttribute("mixValue");
		model.getAttribute("maxValue");
		
		if (!Utilities.validateIfNullOrInvalidDouble(minPrice) && minPrice > Constants.NEGATIVE_ONE) {
			request.getProductInfo().setFromOrgPrice(minPrice);
		} else {
			request.getProductInfo().setFromOrgPrice(Constants.LOWER_LIMIT_PRICE);
		}
		if (!Utilities.validateIfNullOrInvalidDouble(maxPrice) && maxPrice > Constants.NEGATIVE_ONE) {
			request.getProductInfo().setToOrgPrice(maxPrice);
		} else {
			request.getProductInfo().setToOrgPrice(Constants.UPPER_LIMIT_PRICE);
		}
		
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
		return "product-list";
	}
}
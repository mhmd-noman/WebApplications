package com.ultra.fitness.nutritions.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ultra.fitness.nutritions.utils.Constants;

import common.beans.Product;
import common.request.MainRequestObject;
import common.response.MainResponseObject;

@Service
public class ProductService {
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	public MainResponseObject getProducts(MainRequestObject request) {
		MainResponseObject response = null;
		try {
			logger.info(logger.isInfoEnabled() ? "Going to post request for fetching products from getProducts api with requested content: [" +request.getProductInfo()+ "]": null); 
			response = ApiCommunicatorService.postRequest(request, Constants.GET_PRODUCTS);
			logger.info(logger.isInfoEnabled() ? "Response received: [" +response.getResponseCode()+"][" +response.getResponseDesc()+ "]": null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public MainResponseObject addProduct(MainRequestObject request) {
		MainResponseObject response = null;
		try {
			logger.info(logger.isInfoEnabled() ? "Going to post request for adding product from addProduct api with requested content: [" +request.getProductInfo()+ "]": null); 
			response = ApiCommunicatorService.postRequest(request, Constants.ADD_PRODUCT);
			logger.info(logger.isInfoEnabled() ? "Response received: [" +response.getResponseCode()+"][" +response.getResponseDesc()+ "]": null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public MainResponseObject updateProduct(MainRequestObject request) {
		MainResponseObject response = null;
		try {
			logger.info(logger.isInfoEnabled() ? "Going to post request for updating product from updateProduct api with requested content: [" +request.getProductInfo()+ "]": null); 
			response = ApiCommunicatorService.postRequest(request, Constants.UPDATE_PRODUCT);
			logger.info(logger.isInfoEnabled() ? "Response received: [" +response.getResponseCode()+"][" +response.getResponseDesc()+ "]": null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public MainResponseObject deleteProduct(MainRequestObject request) {
		MainResponseObject response = null;
		try {
			logger.info(logger.isInfoEnabled() ? "Going to post request for deleting product from deleteProduct api with requested content: [" +request.getProductInfo()+ "]": null); 
			response = ApiCommunicatorService.postRequest(request, Constants.DELETE_PRODUCT);
			logger.info(logger.isInfoEnabled() ? "Response received: [" +response.getResponseCode()+"][" +response.getResponseDesc()+ "]": null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public MainResponseObject getProducts(Product product) {
		MainRequestObject request = null;
		MainResponseObject response = null;
		try {
			request = new MainRequestObject();
			request.setProductInfo(product);
			logger.info(logger.isInfoEnabled() ? "Going to post request for fetching products to getProducts api with requested content: [" +product+ "]": null); 
			response = ApiCommunicatorService.postRequest(request, Constants.GET_PRODUCTS);
			logger.info(logger.isInfoEnabled() ? "Response received: [" +response.getResponseCode()+"][" +response.getResponseDesc()+ "]": null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public String addProduct(Product product) {
		MainRequestObject request = null;
		MainResponseObject response = null;
		try {
			request = new MainRequestObject();
			request.setProductInfo(product);
			logger.info(logger.isInfoEnabled() ? "Going to post request for adding product to updateProduct api with requested content: [" +product+ "]": null); 
			response = ApiCommunicatorService.postRequest(request, Constants.ADD_PRODUCT);
			logger.info(logger.isInfoEnabled() ? "Response received: [" +response.getResponseCode()+"][" +response.getResponseDesc()+ "]": null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response.getResponseCode();
	}
	
	public String updateProduct(Product product) {
		MainRequestObject request = null;
		MainResponseObject response = null;
		try {
			request = new MainRequestObject();
			request.setProductInfo(product);
			logger.info(logger.isInfoEnabled() ? "Going to post request for updating product to updateProduct api with requested content: [" +product+ "]": null); 
			response = ApiCommunicatorService.postRequest(request, Constants.UPDATE_PRODUCT);
			logger.info(logger.isInfoEnabled() ? "Response received: [" +response.getResponseCode()+"][" +response.getResponseDesc()+ "]": null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response.getResponseCode();
	}
	
	public String deleteProduct(Product product) {
		MainRequestObject request = null;
		MainResponseObject response = null;
		try {
			request = new MainRequestObject();
			request.setProductInfo(product);
			logger.info(logger.isInfoEnabled() ? "Going to post request for deleting product to deleteProduct api with requested content: [" +product+ "]": null); 
			response = ApiCommunicatorService.postRequest(request, Constants.DELETE_PRODUCT);
			logger.info(logger.isInfoEnabled() ? "Response received: [" +response.getResponseCode()+"][" +response.getResponseDesc()+ "]": null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response.getResponseCode();
	}
}

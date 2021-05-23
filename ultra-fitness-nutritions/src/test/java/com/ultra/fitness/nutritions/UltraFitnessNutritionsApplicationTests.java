package com.ultra.fitness.nutritions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ultra.fitness.nutritions.service.ProductService;
import com.ultra.fitness.nutritions.service.UserService;

import common.beans.Product;
import common.request.MainRequestObject;
import common.response.MainResponseObject;
import common.utilities.methods.Utils;

@SpringBootTest
class UltraFitnessNutritionsApplicationTests {
	
	@Autowired
	private static UserService userService;
	@Autowired
	private static ProductService productService;
	
	public static void main(String [] args) throws Exception {
		userService = new UserService();
		productService = new ProductService();
		getProducts(productService);
	}
	
	private static void getProducts(ProductService productService) {
		MainRequestObject request = new MainRequestObject();
		MainResponseObject response = new MainResponseObject();
		Product product = new Product();
		product.setId(1);
		request.setProductInfo(product);
		
		response = productService.getProducts(request);
		System.out.println("Response Code: "+ response.getResponseCode());
		System.out.println("Response Desc: "+ response.getResponseDesc());
		if(!Utils.isNullOrEmptyCollection(response.getProducts())) {
			for (Product pd : response.getProducts()) {
				System.out.println("Product: "+ pd);
			}		
		}
	}
	
	private static void addProduct(ProductService productService) {
		MainRequestObject request = new MainRequestObject();
		MainResponseObject response = new MainResponseObject();
		Product product = new Product();
		product = getProduct();
		request.setProductInfo(product);
		
		response = productService.addProduct(request);
		System.out.println("Response Code: "+ response.getResponseCode());
		System.out.println("Response Desc: "+ response.getResponseDesc());
		if(!Utils.isNullOrEmptyCollection(response.getProducts())) {
			for (Product pd : response.getProducts()) {
				System.out.println("Product: "+ pd);
			}		
		}
	}
	
	private static void updateProduct(ProductService productService) {
		MainRequestObject request = new MainRequestObject();
		MainResponseObject response = new MainResponseObject();
		Product product = new Product();
		product = getProduct();
		request.setProductInfo(product);
		
		response = productService.updateProduct(request);
		System.out.println("Response Code: "+ response.getResponseCode());
		System.out.println("Response Desc: "+ response.getResponseDesc());
		if(!Utils.isNullOrEmptyCollection(response.getProducts())) {
			for (Product pd : response.getProducts()) {
				System.out.println("Product: "+ pd);
			}		
		}
	}
	
	private static void removeProduct(ProductService productService) {
		MainRequestObject request = new MainRequestObject();
		MainResponseObject response = new MainResponseObject();
		Product product = new Product();
		product = getProduct();
		request.setProductInfo(product);
		
		response = productService.deleteProduct(request);
		System.out.println("Response Code: "+ response.getResponseCode());
		System.out.println("Response Desc: "+ response.getResponseDesc());
		if(!Utils.isNullOrEmptyCollection(response.getProducts())) {
			for (Product pd : response.getProducts()) {
				System.out.println("Product: "+ pd);
			}		
		}
	}
	
	private static Product getProduct() {
		Product product2 = new Product();
		product2.setId(2);
		product2.setName("ON Whey");
		product2.setOrderedQuantity(2);
		product2.setOrgPrice(2000.00);
		product2.setDiscount(5.00);
		product2.setRtlPrice(1500.00);
		return product2;
	}
}

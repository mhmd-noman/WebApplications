package com.ultra.fitness.nutritions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.ultra.fitness.nutritions.Beans.User;

import api.communicator.manager.ApiCommunicationManager;
import common.beans.User;
import common.request.MainRequestObject;
import common.response.MainResponseObject;

@Service
public class UserService {
	
//	@Autowired
//	private User user;

	public static String apiName = "createUser";
	
	public String userSignUp(User user) {
		MainRequestObject request = null;
		MainResponseObject response = null;
		String responseCode = null;
		try {
			request = new MainRequestObject();
			user.setUsername("F15");
			request.setUserInfo(user);
			response = ApiCommunicatorService.postRequest(request,apiName);
			responseCode = response.getResponseCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseCode;
	}
	
	

}

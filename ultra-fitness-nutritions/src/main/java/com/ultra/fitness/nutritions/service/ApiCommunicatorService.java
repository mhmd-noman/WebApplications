package com.ultra.fitness.nutritions.service;

import api.communicator.manager.ApiCommunicationManager;
import common.request.MainRequestObject;
import common.response.MainResponseObject;

public class ApiCommunicatorService {

	public static MainResponseObject postRequest(MainRequestObject request, String apiName) throws Exception {
		ApiCommunicationManager acm = new ApiCommunicationManager();
		MainResponseObject response = acm.postRequest(request, apiName);
		return response;
	}
}

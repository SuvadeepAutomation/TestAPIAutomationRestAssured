package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;

import io.restassured.response.Response;

public class GetUserTest {

	static String baseURI = "https://gorest.co.in";
	static String basePath = "/public/v2/users";
	static String token = "38f36092782bd9ad2357f9c077ddc7be0dd264dcd02bcedf3b048229bf4de7cf";

	/**
	 * Method used to trigger get API to get all entire user list
	 * 
	 */
	@Test(priority = -1)
	public void getAllUserList()

	{
		Map<String, String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer " + token);
		Response response = RestClient.doGet("JSON", baseURI, basePath, authTokenMap, null, true);
		System.out.println(response.asPrettyString());
		System.out.println(response.statusCode());

	}

	/**
	 * Method used to trigger get API to get user with the help of Query Parameter
	 * 
	 */
	@Test(priority = 1)
	public static void getUserWithQueryParamsAPITest() {
		Map<String, String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer " + token);
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", "Bhagwati Bhattacharya");
		params.put("gender", "female");

		Response response = RestClient.doGet("JSON", baseURI, basePath, authTokenMap, params, true);
		System.out.println(response.asPrettyString());
		System.out.println(response.statusCode());

	}

}

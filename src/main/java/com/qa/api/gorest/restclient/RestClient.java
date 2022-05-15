package com.qa.api.gorest.restclient;

import java.io.File;
import java.util.Map;

import com.qa.api.gorest.util.TestUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * 
 * This class is having all the http methods which will call the apis and having
 * generic methods for getting the response and fetch the values from response
 * 
 * @author : SuvadeepBasu
 * 
 */

public class RestClient {

	// HTTP Methods : GET POST PUT DELETE

	/**
	 * If RestAssured dependency is not working then remove the scope attribute from
	 * the dependency Supporting document
	 * https://stackoverflow.com/questions/58515714/the-import-io-restassured-restassured-cannot-be-resolved
	 */

	public static Response doGet(String contentType, String baseURI, String basePath, Map<String,String> token,
			Map<String, String> params, boolean logs) {

		if (setBaseURI(baseURI)) {
			RequestSpecification request = createRequest(contentType, token, params, logs);
			return getResponse("GET", request, basePath);
		} else
			return null;
	}

	/**
	 * 
	 * @param contentType
	 * @param baseURI
	 * @param basePath
	 * @param token
	 * @param params
	 * @param logs
	 * @param obj
	 * @return this method is returning response from Post call
	 */

	public static Response doPost(String contentType, String baseURI, String basePath, Map<String, String> token,
			Map<String, String> params, boolean logs, Object obj) {

		if (setBaseURI(baseURI)) {
			RequestSpecification request = createRequest(contentType, token, params, logs);
			addRequestPayload(request, obj);
			return getResponse("POST", request, basePath);
		} else
			return null;
	}

	public static void addRequestPayload(RequestSpecification request, Object obj) {
		if (obj instanceof Map) {
			request.formParams((Map<String, String>) obj);
		} else {
			String jsonPayload = TestUtils.getSerialisedJSON(obj);
			request.body(jsonPayload);
		}
	}

	/**
	 * This method is used to set the baseURI
	 * 
	 * @param : baseURI (This is configured in the request)
	 * 
	 * 
	 */

	private static boolean setBaseURI(String baseURI) {

		if (baseURI == null || baseURI.isEmpty()) {
			System.out.println("Please pass the correct baseURI....either it is null or blank");
			return false;
		}

		try {
			RestAssured.baseURI = baseURI;
			return true;
		} catch (Exception e) {
			System.out.println("Exception occurred while assigning the baseURI");
			return false;
		}

	}

	/**
	 * This method is used to create the API request
	 * 
	 * @param : contentType (Passed in header)
	 * @param : token ( Authorization is passed)
	 * @param : paramsMap ( Query Parameters are passed )
	 * @param : accepts boolean for log generation
	 * 
	 */

	private static RequestSpecification createRequest(String contentType, Map<String, String> token,
			Map<String, String> paramsMap, boolean logs) {

		RequestSpecification request;
		if (logs) {
			request = RestAssured.given().log().all();
		} else {
			request = RestAssured.given();
		}
		if (token.size() > 0) {
			request.headers(token);
			// request.header("Authorization", "Bearer " + token);
		}
		if (!(paramsMap == null)) {
			request.queryParams(paramsMap);
		}
		if (contentType != null) {
			if (contentType.equalsIgnoreCase("JSON")) {
				request.contentType(ContentType.JSON);
			} else if (contentType.equalsIgnoreCase("XML")) {
				request.contentType(ContentType.XML);
			} else if (contentType.equalsIgnoreCase("TEXT")) {
				request.contentType(ContentType.TEXT);
			} else if (contentType.equalsIgnoreCase("multipart")) {
				request.multiPart("image", new File("C:\\Users\\SUVADEEP\\OneDrive\\Desktop\\cloud.jpg"));
			}
		}
		return request;
	}

	/**
	 * This method is used to get the response post triggering the api
	 * 
	 * @param : httpMethod (GET/POST/PUT/DELETE)
	 * @param : request ( Request is prepared in createRequest() method
	 * @param : basePath ( Parameter is passed in Test class)
	 * 
	 */

	private static Response getResponse(String httpMethod, RequestSpecification request, String basePath) {
		return executeApi(httpMethod, request, basePath);
	}

	/**
	 * This method is used to trigger the API
	 * 
	 * @param : httpMethod (GET/POST/PUT/DELETE)
	 * @param : request ( Request is prepared in createRequest() method
	 * @param : basePath ( Parameter is passed in Test class)
	 * 
	 */

	private static Response executeApi(String httpMethod, RequestSpecification request, String basePath) {
		Response response = null;
		switch (httpMethod) {
		case "GET":
			response = request.get(basePath);
			break;
		case "POST":
			response = request.get(basePath);
			break;
		case "PUT":
			response = request.get(basePath);
			break;
		case "DELETE":
			response = request.get(basePath);
			break;
		default:
			System.out.println("Please pass the correct HTTP Method");
			break;
		}
		return response;
	}
}

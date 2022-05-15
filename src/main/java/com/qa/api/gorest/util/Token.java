package com.qa.api.gorest.util;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;

import java.util.HashMap;
import java.util.Map;

import io.restassured.path.json.JsonPath;

public class Token {

	public static Map<Object, Object> appTokenMap;
	public static Map<String, String> tokenMap = new HashMap<String, String>();
	public static String clientId="e92a60894aaa7c5";

	public static Map<Object, Object> getAccessToken() {
		Map<String, String> formParameters = new HashMap<String, String>();
		formParameters.put("refresh_token", "12ed48058c056ae885b5b8a6598a796f89012537");
		formParameters.put("client_id", "e92a60894aaa7c5");
		formParameters.put("client_secret", "50c0f7a8a5b7f532534175358d7712a109c2d708");
		formParameters.put("grant_type", "refresh_token");

		JsonPath tokenJson = given().formParams(formParameters).when().post("https://api.imgur.com/oauth2/token").then()
				.extract().jsonPath();

		appTokenMap = tokenJson.getMap("");// "" gives the complete json
		return appTokenMap;

	}

	public static Map<String, String> getAuthToken() {
		String authToken = appTokenMap.get("access_token").toString();
		System.out.println("Auth Token ===>" + authToken);
		tokenMap.put("Authorization", "Bearer " + authToken);
		return tokenMap;

	}

	public static Map<String, String> getClientId() {

		System.out.println("Client Id is ===>" + clientId);
		tokenMap.put("Authorization", "Client-ID " + clientId);
		return tokenMap;

	}

}

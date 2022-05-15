package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.Token;

import io.restassured.response.Response;

public class GetImgurAPITest {

	Map<Object, Object> tokenMap;
	String accessToken;
	String accUserName;
	String refreshToken;

	@BeforeMethod
	public void setUp() {
		tokenMap = Token.getAccessToken();
		accessToken = tokenMap.get("access_token").toString();
		accUserName = tokenMap.get("account_username").toString();
		refreshToken = tokenMap.get("refresh_token").toString();

	}

	@Test
	public void getAccountBlockAPITest() {
		Map<String,String> authToken=Token.getAuthToken();
		Response response = RestClient.doGet(null, "https://api.imgur.com", "/account/v1/" + accUserName + "/block",
				authToken, null, true);

		System.out.println(response.asPrettyString());
		System.out.println(response.statusCode());

	}

	@Test
	public void getAccountImage() {
		Map<String,String> authTokenMap=Token.getAuthToken();
		Response response = RestClient.doGet(null, "https://api.imgur.com",
				"/3/account/me/images" + accUserName + "/block", authTokenMap, null, true);

		System.out.println(response.asPrettyString());
		System.out.println(response.statusCode());

	}

	@Test
	public static void uploadImagePOSTApiTest() {
		/**
		 * for uploading image we need to pass multipart as ContentType
		 */

		Map<String, String> clientIDMap = Token.getClientId();
		Map<String, String> formMap = new HashMap<String, String>();
		formMap.put("title", "test title API");
		formMap.put("description", "test description api");
		Response response = RestClient.doPost("multipart", "https://api.imgur.com", "/3/upload", clientIDMap, null,
				true, formMap);
		System.out.println(response.prettyPrint());
		System.out.println(response.statusCode());

	}

}
